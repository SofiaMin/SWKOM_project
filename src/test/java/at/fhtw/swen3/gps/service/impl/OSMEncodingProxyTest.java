package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import fr.dudie.nominatim.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OSMEncodingProxyTest {

    private static Address a;
    private OSMEncodingProxy osmEncoding ;

    @BeforeEach
    void setUp() {
        a = new Address();
        a.setDisplayName("Vienna, Austria");

        osmEncoding = new OSMEncodingProxy();
    }

    @Test
    void encodeAddress() {

        assertEquals(osmEncoding.encodeAddress(a), new GeoCoordinate(48.2083537,16.3725042));
    }
}