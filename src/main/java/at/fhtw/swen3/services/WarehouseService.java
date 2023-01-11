package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

import java.util.List;

public interface WarehouseService {
    Hop getWarehouse(String code);
    void importWarehouses(Warehouse warehouse);
    Warehouse exportWarehouse();
}

