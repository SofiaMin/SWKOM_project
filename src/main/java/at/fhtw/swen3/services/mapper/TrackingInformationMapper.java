package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackingInformationMapper {
    TrackingInformationMapper INSTANCE = Mappers.getMapper(TrackingInformationMapper.class);

    /**
     * Dto and Entity Mapping
     */
    @Mapping(source = "trackingInformation.state", target = "state")
    @Mapping(source = "trackingInformation.visitedHops", target = "visitedHops")
    ParcelEntity dtoToEntity (TrackingInformation trackingInformation);

    @Mapping(source = "parcelEntity.state", target = "state")
    @Mapping(source = "parcelEntity.visitedHops", target = "visitedHops")
    TrackingInformation entityToDto (ParcelEntity parcelEntity);
}