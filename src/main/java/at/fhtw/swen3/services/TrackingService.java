package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;

public interface TrackingService {
    void predictFutureHops(ParcelEntity parcelEntity);
}
