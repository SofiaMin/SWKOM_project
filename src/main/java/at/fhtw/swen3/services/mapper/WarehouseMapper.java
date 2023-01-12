package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {GeoCoordinateMapper.class, WarehouseNextHopsMapper.class})
@DecoratedWith(WarehouseMapperDecorator.class)
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    /**
     * Dto and Entity Mapping
     */
    WarehouseEntity dtoToEntity(Warehouse warehouse);
    Warehouse entityToDto(WarehouseEntity warehouseEntity);
}