package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.TrackingService;
import at.fhtw.swen3.services.WebhookManager;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.TrackingInformationMapper;
import at.fhtw.swen3.services.validation.Validator;
import at.fhtw.swen3.util.UUIDGenerator;
import at.fhtw.swen3.util.exceptions.BLDataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    private final Validator validator;
    private final RecipientRepository recipientRepository;
    private final ParcelRepository parcelRepository;
    private final TrackingService trackingService;
    private final HopRepository hopRepository;
    private final WebhookManager webhookManager;

    @Override
    public ParcelEntity submitNewParcel(ParcelEntity parcelEntity) {
        log.info("submitNewParcel() with parcel: " + parcelEntity);
        parcelEntity.setTrackingId(UUIDGenerator.generateUUID());
        parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
        parcelEntity.setVisitedHops(new ArrayList<>());
        parcelEntity.setFutureHops(new ArrayList<>());
        this.validator.validate(parcelEntity);
        this.recipientRepository.saveAndFlush(parcelEntity.getSender());
        this.recipientRepository.saveAndFlush(parcelEntity.getRecipient());
        this.parcelRepository.saveAndFlush(parcelEntity);
        trackingService.predictFutureHops(parcelEntity);
        parcelRepository.save(parcelEntity);
        return parcelEntity;
    }

    @Override
    @Transactional
    public ParcelEntity transferParcel(ParcelEntity parcelEntity, String trackingID) {
        log.info("transferParcel() with parcel: " + parcelEntity);
        this.validator.validate(parcelEntity);
        parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
        parcelEntity.setTrackingId(trackingID);
        parcelEntity.setVisitedHops(new ArrayList<>());
        parcelEntity.setFutureHops(new ArrayList<>());
        this.recipientRepository.save(parcelEntity.getSender());
        this.recipientRepository.save(parcelEntity.getRecipient());
        this.parcelRepository.save(parcelEntity);
        trackingService.predictFutureHops(parcelEntity);
        parcelRepository.save(parcelEntity);
        return parcelEntity;
    }

    @Override
    @Transactional
    public void reportParcel(String trackingId) {
        log.info("reportParcel");
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new BLDataNotFoundException("no parcel found with trackingID = " + trackingId));
        parcel.setState(TrackingInformation.StateEnum.DELIVERED);
        parcel.setFutureHops(List.of());
        //parcelRepository.save(parcel);
        webhookManager.notifySubscribers(parcel.getTrackingId(), TrackingInformationMapper.INSTANCE.entityToDto(parcel));
        webhookManager.clearSubscribers(parcel.getTrackingId());
    }

    @Override
    public void updateParcel(String trackingID, String code) {
        log.info("updateParcel() with trackingId=" + trackingID + " , code: " + code);
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingID)
                .orElseThrow(() -> new BLDataNotFoundException("no parcel found with trackingID = " + trackingID));
        Optional<HopArrivalEntity> first = parcelEntity.getFutureHops().stream().filter(e -> e.getCode().equals(code)).findFirst();
        if (first.isPresent()) {
            HopArrivalEntity hopArrivalEntity = first.get();
            parcelEntity.getFutureHops().remove(hopArrivalEntity);
            parcelEntity.getVisitedHops().add(hopArrivalEntity);
            hopArrivalEntity.setVisited(true);
            Optional<HopEntity> byCode = hopRepository.findByCode(code);
            if (byCode.isPresent()) {
                HopEntity hopEntity = byCode.get();
                String hopType = hopEntity.getHopType();
                switch (hopType) {
                    case "truck":
                        parcelEntity.setState(TrackingInformation.StateEnum.INTRUCKDELIVERY);
                        break;
                    case "warehouse":
                        parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
                        break;
                    case "transferwarehouse":
                        sendHttp(parcelEntity.getTrackingId(), ((TransferwarehouseEntity)hopEntity).getLogisticsPartnerUrl());
                        parcelEntity.setState(TrackingInformation.StateEnum.TRANSFERRED);
                        break;
                }
            } else {
                log.info("no such code in future hops!");
            }
            parcelRepository.save(parcelEntity);
            webhookManager.notifySubscribers(parcelEntity.getTrackingId(), TrackingInformationMapper.INSTANCE.entityToDto(parcelEntity));
        } else {
            log.info("no such code in future hops!");
        }
    }

    private void sendHttp(String trackingID, String partnerUrl) {
        OkHttpClient client = new OkHttpClient();
        String URL = "https://" + partnerUrl + "/parcel/" + trackingID;
        Request request = new Request.Builder()
                .url(URL)
                .post(RequestBody.create("{}", MediaType.get("json")))
                .build();
        try {
            Response execute = client.newCall(request).execute();
            execute.close();
        } catch (IOException ignored) {}
    }

    @Override
    public void deleteParcel(String id) {
        log.info("deleteParcel() with id=" + id);
        this.parcelRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public List<Parcel> getParcels() {
        log.info("getParcels()");
        List<Parcel> parcelDtos = new ArrayList<>();
        List<ParcelEntity> parcelEntities = this.parcelRepository.findAll();
        for(ParcelEntity parcelEntity : parcelEntities) {
            parcelDtos.add(ParcelMapper.INSTANCE.entityToDto(parcelEntity));
        }
        return parcelDtos;
    }
    @Override
    public ParcelEntity findByTrackingId(String id) {
        log.info("get parcel with id " + id);
        return parcelRepository.findByTrackingId(id)
                .orElseThrow(() -> new BLDataNotFoundException("no parcel found with trackingID = " + id));
    }
}
