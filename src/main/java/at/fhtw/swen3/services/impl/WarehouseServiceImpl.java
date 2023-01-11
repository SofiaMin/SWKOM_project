package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.persistence.repositories.GeoCoordinateRepository;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.TruncateRepositoryImpl;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.Validator;
import at.fhtw.swen3.util.exceptions.BLDataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    private final Validator validator;
    private final WarehouseRepository warehouseRepository;
    private final GeoCoordinateRepository geoCoordinateRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;
    private final HopRepository hopRepository;

    @Autowired
    private TruncateRepositoryImpl truncateRepository;

    @Override
    public Hop getWarehouse(String code) {
        log.info("getWarehouse()");
        HopEntity warehouseEntity = hopRepository.findByCode(code)
                .orElseThrow(() -> new BLDataNotFoundException("no warehouse found with code = " + code));
        return HopMapper.INSTANCE.entityToDto(warehouseEntity);
    }

    @Override
    public void importWarehouses(Warehouse warehouse) {
        log.info("importWarehouses()");
        //truncateRepository.truncate();
        validator.validate(warehouse);
        WarehouseEntity entity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        warehouseRepository.save(entity);
    }

    @Override
    public Warehouse exportWarehouse() {
        log.info("exportWarehouse()");
        WarehouseEntity first = warehouseRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new BLDataNotFoundException("no warehouse found"));
        return WarehouseMapper.INSTANCE.entityToDto(first);
    }
}
