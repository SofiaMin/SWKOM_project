package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OpenApiGeneratorApplication.class)
class RecipientMapperTest {


    @Test
    void TestRecipientDtoToEntity() {
        Recipient recipient = new Recipient();
        recipient.setStreet("Zieglergasse 21-23");
        recipient.setPostalCode("A-1070");
        recipient.setCity("Wien");
        recipient.setCountry("Österreich");
        recipient.setName("GTVS Neubau");

        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);

        assertEquals(recipient.getStreet(), recipientEntity.getStreet());
        assertEquals(recipient.getCountry(), recipientEntity.getCountry());
        assertEquals(recipient.getCity(), recipientEntity.getCity());
        assertEquals(recipient.getPostalCode(), recipientEntity.getPostalCode());
        assertEquals(recipient.getName(), recipientEntity.getName());

    }

    @Test
    void TestRecipientEntityToDto() {
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setName("GTVS Neubau");
        recipientEntity.setStreet("Zieglergasse 21-23");
        recipientEntity.setPostalCode("A-1070");
        recipientEntity.setCity("Wien");
        recipientEntity.setCountry("Österreich");

        Recipient recipient = RecipientMapper.INSTANCE.entityToDto(recipientEntity);

        assertEquals(recipient.getStreet(), recipientEntity.getStreet());
        assertEquals(recipient.getCountry(), recipientEntity.getCountry());
        assertEquals(recipient.getCity(), recipientEntity.getCity());
        assertEquals(recipient.getPostalCode(), recipientEntity.getPostalCode());
        assertEquals(recipient.getName(), recipientEntity.getName());
    }

}