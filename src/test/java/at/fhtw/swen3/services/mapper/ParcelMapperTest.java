package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParcelMapperTest {
    @Test
    void dtoToEntity() {
        //List<HopArrivalEntity> hopArrivals = new ArrayList<>().add(new HopArrivalEntity(123L, "hallo", ));
        final Parcel parcel = new Parcel()
                .weight(2.0f)
                .sender(new Recipient()
                        .name("Herbert")
                        .city("Vienna"))
                .recipient(new Recipient()
                        .name("Gustav")
                        .city("Berlin")
                );

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
        assertEquals(parcel.getWeight(), parcelEntity.getWeight());
        assertEquals(parcel.getSender().getName(), parcelEntity.getSender().getName());
        assertEquals(parcel.getSender().getCity(), parcelEntity.getSender().getCity());
    }

    @Test
    void dtoToEntityEmptyStreet() {
        final Parcel parcel = new Parcel()
                .weight(2.0f)
                .sender(new Recipient()
                        .name("Herbert")
                        .city("Vienna"))
                .recipient(new Recipient()
                        .name("Gustav")
                        .city("Berlin")
                );

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
        assertNull(parcelEntity.getSender().getStreet());
    }

    @Test
    void entityToDto() {
        final ParcelEntity parcelEntity = new ParcelEntity()
                .weight(2.0f)
                .sender(new RecipientEntity()
                        .name("Herbert")
                        .city("Vienna"))
                .recipient(new RecipientEntity()
                        .name("Gustav")
                        .city("Berlin")
                );

        Parcel parcel = ParcelMapper.INSTANCE.entityToDto(parcelEntity);

        assertEquals(parcelEntity.getWeight(), parcel.getWeight());
        assertEquals(parcelEntity.getSender().getCity(), parcel.getSender().getCity());
        assertNull(parcel.getSender().getStreet());
    }
}
