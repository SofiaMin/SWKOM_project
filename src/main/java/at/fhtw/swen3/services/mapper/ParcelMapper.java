/*package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParcelMapper {

    ParcelMapper INSTANCE = Mappers.getMapper( ParcelMapper.class );

    @Mapping(source = "parcel.weight", target = "weight")
    @Mapping(source = "parcel.recipient", target = "recipient")
    @Mapping(source = "parcel.sender", target = "sender")
    @Mapping(source = "newParcelInfo.trackingId", target = "trackingId")
    @Mapping(source = "trackingInformation.state", target = "state")
    @Mapping(source = "trackingInformation.futureHops", target = "futureHops")
    @Mapping(source = "trackingInformation.visitedHops", target = "visitedHops")

    ParcelEntity dtoToEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation);



}*/
package at.fhtw.swen3.services.mapper;

        import at.fhtw.swen3.services.dto.NewParcelInfo;
        import at.fhtw.swen3.services.dto.Parcel;
        import at.fhtw.swen3.services.dto.TrackingInformation;
        import at.fhtw.swen3.persistence.entity.ParcelEntity;
        import org.mapstruct.Mapper;
        import org.mapstruct.Mapping;
        import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper( ParcelMapper.class );

    /*@Mapping(source="parcel.weight", target = "weight")
    @Mapping(source="parcel.recipient", target = "recipient")
    @Mapping(source="parcel.sender", target = "sender")
    @Mapping(source="newParcelInfo.trackingId", target = "trackingId")
    @Mapping(source="trackingInformation.state", target = "state")
    @Mapping(source="trackingInformation.futureHops", target = "futureHops")
    @Mapping(source="trackingInformation.visitedHops", target = "visitedHops")*/
    ParcelEntity dtoToEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation);
    //Parcel EntityToDtoParcel(ParcelEntity parcelEntity);
    //NewParcelInfo EntityToDtoNewParcelInfo(ParcelEntity parcelEntity);
    //TrackingInformation EntityToDtoTrackingInformation(ParcelEntity parcelEntity);
}

