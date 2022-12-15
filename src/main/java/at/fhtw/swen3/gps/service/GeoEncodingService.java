package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import fr.dudie.nominatim.model.Address;

import java.io.IOException;

public interface GeoEncodingService {

    GeoCoordinate encodeAddress(Address a);
}
