package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class WarehouseNextHopsRepositoryTest {
    @Autowired
    WarehouseNextHopsRepository repo;

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    WarehouseNextHopsEntity warehouseNextHops;

    @BeforeEach
    void SetUp() {
        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);

        HopEntity hop = new HopEntity();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity); hop.setHopType("V");

        HopEntity newHop = hopRepository.save(hop);

        warehouseNextHops = WarehouseNextHopsEntity.builder().hopEntity(newHop).traveltimeMins(34).build();

    }

    @Test
    void save_checkIdNotNull() {
        WarehouseNextHopsEntity newWarehouseNextHopsEntity = repo.save(warehouseNextHops);
        assertNotNull(newWarehouseNextHopsEntity.getId());

    }

}