package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;

import java.util.stream.Collectors;

public abstract class WarehouseMapperDecorator implements WarehouseMapper {

    private final WarehouseMapper delegate;

    protected WarehouseMapperDecorator(WarehouseMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public WarehouseEntity dtoToEntity(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setCode(warehouse.getCode());
        warehouseEntity.setDescription(warehouse.getDescription());
        warehouseEntity.setProcessingDelayMins(warehouse.getProcessingDelayMins());
        warehouseEntity.setLocationName(warehouse.getLocationName());
        warehouseEntity.setHopType(warehouse.getHopType());
        warehouseEntity.setLocationCoordinates(GeoCoordinateMapper.INSTANCE.dtoToEntity(warehouse.getLocationCoordinates()));
        warehouseEntity.setLevel(warehouse.getLevel());
        warehouseEntity.setNextHops(warehouse.getNextHops().stream().map(WarehouseNextHopsMapper.INSTANCE::dtoToEntity).collect(Collectors.toList()));
        return warehouseEntity;
    }

    @Override
    public Warehouse entityToDto(WarehouseEntity warehouseEntity) {
        Warehouse warehouse = new Warehouse();
        warehouse.setCode(warehouseEntity.getCode());
        warehouse.setDescription(warehouseEntity.getDescription());
        warehouse.setProcessingDelayMins(warehouseEntity.getProcessingDelayMins());
        warehouse.setLocationName(warehouseEntity.getLocationName());
        warehouse.setHopType(warehouseEntity.getHopType());
        return delegate.entityToDto(warehouseEntity);
    }
}
