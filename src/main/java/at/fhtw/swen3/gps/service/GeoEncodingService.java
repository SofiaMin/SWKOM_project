package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.apache.tomcat.jni.Address;

public interface GeoEncodingService {

        GeoCoordinate encodeAddress(Address a);
}
