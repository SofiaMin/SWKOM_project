package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.HopEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("src/test/resources/application-test.properties")
class HopRepositoryTest {

    @Autowired
    private HopRepository repo;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    HopEntity hopEntity;

    @BeforeEach
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(48.12).lon(16.20).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);

        hopEntity = new HopEntity();
        hopEntity.setCode("A-1070");
        hopEntity.setDescription("Description: ... ");
        hopEntity.setProcessingDelayMins(2);
        hopEntity.setLocationName("Vienna");
        hopEntity.setLocationCoordinates(newGeoCoordinateEntity);
        hopEntity.setHopType("V");
    }

    @Test
    void saveHopEntityTest() {
        HopEntity newHopEntity = repo.save(hopEntity);
        assertNotNull(newHopEntity.getId());
        assertEquals(newHopEntity.getCode(), hopEntity.getCode());
    }

}