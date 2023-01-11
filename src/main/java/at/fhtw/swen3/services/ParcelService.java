package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;

import java.util.List;

public interface ParcelService {
    ParcelEntity submitNewParcel(ParcelEntity parcelEntity);

    ParcelEntity transferParcel(ParcelEntity parcelEntity, String trackingID);

    void reportParcel(String trackingId);
    void updateParcel(String id, String code);
    void deleteParcel(String id);
    List<Parcel> getParcels();
    ParcelEntity findByTrackingId(String id);
}
