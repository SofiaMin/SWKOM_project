package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    private final Validator validator;
    private final WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> getWarehouse() {
        log.info("getWarehouse()");
        return null;
    }

    @Override
    public void importWarehouses() {
        log.info("importWarehouses()");
    }

    @Override
    public void exportWarehouse() {
        log.info("exportWarehouse()");
    }
}
