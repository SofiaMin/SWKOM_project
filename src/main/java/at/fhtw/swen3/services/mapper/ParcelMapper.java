package at.fhtw.swen3.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelMapper {
    @Mapping(source = "parcelDto.weight", target = "weight")
    @Mapping(source = "parcelDto.recipient", target = "recipient")
    @Mapping(source = "parcelDto.recipient", target = "recipient")




}
