package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RecipientMapper.class)
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    /**
     * Dto and Entity Mapping
     */
    @Mapping(source = "recipient", target = "recipient")
    ParcelEntity dtoToEntity(Parcel parcel);
    Parcel entityToDto(ParcelEntity parcelEntity);
}
