package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ParcelRepositoryTest {

    @Autowired
    private ParcelRepository repo;

    private static ParcelEntity parcelEntity;

    @BeforeAll
    static void setUp() {
        parcelEntity = new ParcelEntity()
                .weight(1.0f)
                .sender(new RecipientEntity()
                        .name("Schnittler")
                        .city("Vienna"))
                .recipient(new RecipientEntity()
                        .name("Schneider")
                        .city("Munich")
                );
    }

    @Test
    void saveParcelEntity() {
        ParcelEntity parcel = repo.save(parcelEntity);
        assertNotNull(parcel.getId());
        assertEquals(parcel.getWeight(), parcelEntity.getWeight());
        assertEquals(parcel.getSender(), parcelEntity.getSender());
        assertEquals(parcel.getRecipient(), parcelEntity.getRecipient());
    }
}