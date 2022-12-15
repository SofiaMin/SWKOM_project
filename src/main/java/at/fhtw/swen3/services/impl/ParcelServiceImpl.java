package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.Validator;
import at.fhtw.swen3.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    private final Validator validator;
    private final RecipientRepository recipientRepository;
    private final ParcelRepository parcelRepository;
    UUIDGenerator uuidGenerator;

    @Override
    public void submitNewParcel(ParcelEntity parcelEntity) {
        log.info("submitNewParcel() with parcel: " + parcelEntity);
        parcelEntity.setTrackingId(uuidGenerator.generateUUID());
        parcelEntity.setState(TrackingInformation.StateEnum.PICKUP);
        parcelEntity.setVisitedHops(new ArrayList<HopArrivalEntity>());
        parcelEntity.setFutureHops(new ArrayList<HopArrivalEntity>());
        this.validator.validate(parcelEntity);
        this.recipientRepository.save(parcelEntity.getSender());
        this.recipientRepository.save(parcelEntity.getRecipient());
        this.parcelRepository.save(parcelEntity);
    }

    @Override
    public void updateParcel(String id, ParcelEntity parcelEntity) {
        log.info("updateParcel() with id=" + id + " , parcel: " + parcelEntity);
        this.parcelRepository.save(parcelEntity);
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
        return parcelRepository.findByTrackingId(id);
    }
}
