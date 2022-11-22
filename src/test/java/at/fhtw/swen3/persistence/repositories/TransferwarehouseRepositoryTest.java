package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("src/test/resources/application-test.properties")
class TransferwarehouseRepositoryTest {

    @Autowired
    TransferwarehouseRepository repo;

    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;

    TransferwarehouseEntity transferwarehouseEntity;

    @Test
    void setUp() {

        /*GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);
        HopEntity hop = new HopEntity();
        hop.setCode("A-1070");
        hop.setDescription("Description: ...");
        hop.setProcessingDelayMins(2);
        hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity);
        hop.setHopType("R");

        HopEntity newHop = hopRepository.save(hop);

        transferwarehouseEntity = new TransferwarehouseEntity();
        transferwarehouseEntity.setLogisticsPartner("Logistics Partner");
        transferwarehouseEntity.setRegionGeoJson("Vienna");
        transferwarehouseEntity.setLogisticsPartnerUrl("logistics Partner url");*/


    }

    @Test
    void saveTest() {

        /*TransferwarehouseEntity TransferwarehouseRepositoryTest = repo.save(transferwarehouseEntity);
        assertNotNull(TransferwarehouseRepositoryTest.getId());*/


    }

}