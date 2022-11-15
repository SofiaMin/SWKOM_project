package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;

import java.util.List;

public interface ParcelService {
    public abstract void submitNewParcel(ParcelEntity parcelEntity);
    public abstract void updateParcel(String id, ParcelEntity parcelEntity);
    public abstract void deleteParcel(String id);
    public abstract List<Parcel> getParcels();
}
