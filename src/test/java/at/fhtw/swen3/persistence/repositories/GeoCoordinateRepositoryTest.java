package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeoCoordinateRepositoryTest {
    @Autowired
    private GeoCoordinateRepository repo;

    GeoCoordinateEntity geoCoordinateEntity;

    @BeforeEach
    void setUp() {
        geoCoordinateEntity = GeoCoordinateEntity.builder()
                .lon(55.3)
                .lat(43.4).build();
    }

    @Test
    void saveGeoCoordinateEntityTest() {
        GeoCoordinateEntity geoCoordinateEntityTest = repo.save(geoCoordinateEntity);
        assertNotNull(geoCoordinateEntityTest.getId());
        assertNotNull(geoCoordinateEntityTest.getLat());
        assertNotNull(geoCoordinateEntityTest.getLon());
    }

}