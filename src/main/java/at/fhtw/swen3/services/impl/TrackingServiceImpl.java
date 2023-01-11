package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.OSMEncodingProxy;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.TrackingService;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.util.exceptions.BLDataNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import fr.dudie.nominatim.model.Address;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wololo.geojson.GeoJSONFactory;
import org.wololo.jts2geojson.GeoJSONReader;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final WarehouseRepository warehouseRepository;
    private final HopArrivalRepository hopArrivalRepository;
    private final ParcelRepository parcelRepository;
    private final GeoEncodingService geoEncodingService;
    private final TruckRepository truckRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Override
    @Transactional
    public void predictFutureHops(ParcelEntity parcelEntity) {
        log.info("predicting future hops");
        RecipientEntity recipient = parcelEntity.getRecipient();
        RecipientEntity sender = parcelEntity.getSender();
        GeoCoordinate recipientGeoCoordinates = getGeoCoordinates(recipient);
        GeoCoordinate senderGeoCoordinates = getGeoCoordinates(sender);
        List<HopArrivalEntity> futureHops = new ArrayList<>();
        List<TruckEntity> allTrucks = truckRepository.findAll();

        TruckEntity senderHop = findHopByGeoCoordinates(allTrucks, senderGeoCoordinates);
        TruckEntity recipientHop = findHopByGeoCoordinates(allTrucks, recipientGeoCoordinates);

        futureHops.add(convertHopToArrivalEntity(senderHop, OffsetDateTime.now()));
        find(futureHops, senderHop, recipientHop);
        futureHops.add(convertHopToArrivalEntity(recipientHop, getLastDateTime(futureHops)));

        futureHops.forEach(e -> e.setFk_parcel(parcelEntity));

        parcelEntity.setFutureHops(futureHops);
    }

    private List<HopArrivalEntity> find(List<HopArrivalEntity> list, HopEntity senderHop, HopEntity recipientHop) {
        WarehouseEntity warehouseSender = findWarehouse(senderHop);
        WarehouseEntity warehouseRecipient = findWarehouse(recipientHop);
        if (warehouseSender.getLevel().equals(warehouseRecipient.getLevel())) {
            list.add(convertHopToArrivalEntity(warehouseSender, getLastDateTime(list)));
            return list;
        }
        if (warehouseSender.getLevel() > warehouseRecipient.getLevel()) {
            return find(list, senderHop, warehouseRecipient);
        } else {
            return find(list, warehouseSender, recipientHop);
        }
    }

    private OffsetDateTime getLastDateTime(List<HopArrivalEntity> list) {
        return list.stream().skip(list.size() - 1).findFirst().orElseThrow(NoSuchElementException::new).getDateTime();
    }

    private WarehouseEntity findWarehouse(HopEntity hopEntity) {
        return warehouseRepository.findWarehouseByHopId(hopEntity.getId())
                .orElseThrow(() -> new BLDataNotFoundException("warehouse not found for hop " + hopEntity.getId()));
    }

    private GeoCoordinate getGeoCoordinates(RecipientEntity recipient) {
        Address address = new Address();
        address.setDisplayName(recipient.getStreet() + ", " + recipient.getPostalCode() + ", " + recipient.getCity() + ", " + recipient.getCountry());
        return geoEncodingService.encodeAddress(address);
    }

    private TruckEntity findHopByGeoCoordinates(List<TruckEntity> truckEntities, GeoCoordinate geoCoordinate) {
        for (TruckEntity truckEntity : truckEntities) {
            String regionGeoJson = truckEntity.getRegionGeoJson();
            Geometry geometry = parseGeoJsonToGeometry(regionGeoJson);
            Point point = convertGeoCoordinateToPoint(geoCoordinate);
            if (geometry.contains(point)) {
                return truckEntity;
            }
        }
        throw new BLDataNotFoundException("Sender hop not found!");
    }

    @SneakyThrows
    private Geometry parseGeoJsonToGeometry(String geoJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(geoJson);
        JsonNode geometry = jsonNode.get("geometry");
        org.wololo.geojson.Geometry geoJSON = (org.wololo.geojson.Geometry) GeoJSONFactory.create(geometry.toString());
        return new GeoJSONReader().read(geoJSON);
    }

    private Point convertGeoCoordinateToPoint(GeoCoordinate geoCoordinate) {
        return new GeometryFactory().createPoint(new Coordinate(geoCoordinate.getLon(), geoCoordinate.getLat()));
    }

    private HopArrivalEntity convertHopToArrivalEntity(HopEntity hopEntity, OffsetDateTime offsetDateTime) {
        return HopArrivalEntity.builder()
                .code(hopEntity.getCode())
                .description(hopEntity.getDescription())
                .visited(false)
                .dateTime(offsetDateTime.plusMinutes(hopEntity.getProcessingDelayMins()))
                .build();
    }
}
