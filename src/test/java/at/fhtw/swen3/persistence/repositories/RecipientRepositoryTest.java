package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("src/test/resources/application-test.properties")
class RecipientRepositoryTest {

    @Autowired
    private RecipientRepository repo;

    private static RecipientEntity recipientEntity;

    @BeforeAll
    static void setUp() {
        recipientEntity = RecipientEntity.builder().name("Mladen").street("Lindengasse 6")
                .postalCode("A-1070").city("Vienna").country("Austria").build();
    }

    @Test
    void saveRecipientEntityTest() {

        RecipientEntity recipient = repo.save(recipientEntity);
        assertNotNull(recipient.getId());
        assertEquals(recipient.getCity(), recipientEntity.getCity());

    }

}