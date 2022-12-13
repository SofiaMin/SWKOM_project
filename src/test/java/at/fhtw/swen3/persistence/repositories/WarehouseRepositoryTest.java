package at.fhtw.swen3.persistence.repositories;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WarehouseRepositoryTest {
    /*@Autowired
    private WarehouseRepository repo;

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    WarehouseEntity warehouseEntity;

    @BeforeEach
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);
        HopEntity hop = new HopEntity();

        HopEntity newHop = hopRepository.save(hop);

        WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsEntity.builder().hopEntity(newHop).traveltimeMins(15).build();
        WarehouseNextHopsEntity newWarehouseNextHopsEntity = warehouseNextHopsRepository.save(warehouseNextHopsEntity);

        List<WarehouseNextHopsEntity> hops = new ArrayList<>();
        hops.add(newWarehouseNextHopsEntity);

        //Since warehouseEntity extends from Hopentity, we also need to implement the attributes for HopEntity.
        //GeoCoordinateEntity geoCoordinateForWarehouse = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        //GeoCoordinateEntity newGeoCoordinateForWarehouse = geoCoordinateRepository.save(geoCoordinateForWarehouse);
        warehouseEntity = WarehouseEntity.builder().level(4).nextHops(hops).build();
    }

    @Test
    void saveTest_checkId() {
        WarehouseEntity warehouseEntityTest = repo.save(warehouseEntity);
        assertNotNull(warehouseEntityTest.getId());
    }*/

}