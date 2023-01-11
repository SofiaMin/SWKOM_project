package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.TruncateRepositoryImpl;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class ParcelServiceTest {

    @Autowired
    private ParcelService parcelService;

    @Autowired
    private TruncateRepositoryImpl truncateRepositoty;

    @BeforeEach
    public void init() {
        truncateRepositoty.truncate();
    }

    @Test
    void submitParcelTest() {
        RecipientEntity recipient = RecipientEntity.builder()
                .city("New York")
                .country("US")
                .name("Test1")
                .postalCode("CCCC")
                .street("st")
                .build();
        RecipientEntity sender = RecipientEntity.builder()
                .city("New York")
                .country("US")
                .name("Test2")
                .postalCode("AAAA")
                .street("st")
                .build();
        ParcelEntity parcel = ParcelEntity.builder()
                .weight(10)
                .recipient(recipient)
                .sender(sender)
                .build();
        ParcelEntity parcel1 = parcelService.submitNewParcel(parcel);
        assertEquals(parcel, parcel1);
    }

    @Test
    void testFindByTrackingId() {
        ParcelEntity parcel1 = createDefaultParcel();
        ParcelEntity foundParcel = parcelService.findByTrackingId(parcel1.getTrackingId());
        assertEquals(parcel1.getTrackingId(), foundParcel.getTrackingId());
        assertEquals(parcel1.getWeight(), foundParcel.getWeight());
        assertEquals(parcel1.getState(), foundParcel.getState());
    }

    @Test
    void reportParcelTest() {
        ParcelEntity parcel1 = createDefaultParcel();
        String trackingId = parcel1.getTrackingId();
        parcelService.reportParcel(trackingId);
        ParcelEntity byTrackingId = parcelService.findByTrackingId(trackingId);
        assertEquals(TrackingInformation.StateEnum.DELIVERED, byTrackingId.getState());
    }

    @Test
    void transferParcelTest() {
        ParcelEntity defaultParcel = createDefaultParcel();
        ParcelEntity parcel = parcelService.transferParcel(defaultParcel, "ABC888ADS");
        ParcelEntity abc888ADS = parcelService.findByTrackingId("ABC888ADS");
        assertEquals(parcel.getId(), abc888ADS.getId());
        assertEquals(parcel.getState(), abc888ADS.getState());
        assertEquals(parcel.getWeight(), abc888ADS.getWeight());
    }

    private ParcelEntity createDefaultParcel() {
        RecipientEntity recipient = RecipientEntity.builder()
                .city("New York")
                .country("US")
                .name("Test1")
                .postalCode("CCCC")
                .street("st")
                .build();
        RecipientEntity sender = RecipientEntity.builder()
                .city("New York")
                .country("US")
                .name("Test2")
                .postalCode("AAAA")
                .street("st")
                .build();
        ParcelEntity parcel = ParcelEntity.builder()
                .weight(10)
                .recipient(recipient)
                .sender(sender)
                .build();
        return parcelService.submitNewParcel(parcel);
    }
}
