package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = HopMapper.class)
public interface WarehouseNextHopsMapper {
    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);

    /**
     * Dto and Entity Mapping
     */
    @Mapping(source = "hop", target = "hopEntity")
    WarehouseNextHopsEntity dtoToEntity(WarehouseNextHops warehouseNextHops);
    @Mapping(source = "hopEntity", target = "hop")
    WarehouseNextHops entityToDto(WarehouseNextHopsEntity warehouseNextHopsEntity);
}
