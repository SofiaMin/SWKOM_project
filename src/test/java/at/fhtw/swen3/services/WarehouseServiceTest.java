package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    void importWarehouseTest() {
        Warehouse warehouse = createDefaultWarehouse();
        warehouseService.importWarehouses(warehouse);
    }

    @Test
    void exportWarehouseTest() {
        Warehouse warehouse = createDefaultWarehouse();
        warehouseService.importWarehouses(warehouse);
        Warehouse exported = warehouseService.exportWarehouse();
        assertEquals(warehouse, exported);
    }

    private Warehouse createDefaultWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setDescription("desc");
        warehouse.setLevel(1);
        warehouse.setHopType("warehouse");
        warehouse.setLocationName("wh");
        warehouse.setProcessingDelayMins(1);
        warehouse.setCode("ABCD1234");
        warehouse.setLocationCoordinates(new GeoCoordinate(1.0, 2.0));
        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();
        Truck truck = new Truck();
        truck.setNumberPlate("123");
        truck.setHopType("truck");
        truck.setCode("AAAA1111");
        truck.setRegionGeoJson("{1, 2}");
        truck.setLocationCoordinates(new GeoCoordinate(2.0, 3.0));
        truck.setDescription("desc");
        truck.setLocationName("asd");
        truck.setProcessingDelayMins(10);
        warehouseNextHops.setHop(truck);
        warehouseNextHops.setTraveltimeMins(123);
        warehouse.setNextHops(List.of(warehouseNextHops));
        return warehouse;
    }
}
