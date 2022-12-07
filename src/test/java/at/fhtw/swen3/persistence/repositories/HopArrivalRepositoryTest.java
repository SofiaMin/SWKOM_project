package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class HopArrivalRepositoryTest {

    @Autowired
    private HopArrivalRepository repo;
    HopArrivalEntity hopArrivalEntity;

    @BeforeEach
    void setUp() {
        hopArrivalEntity = HopArrivalEntity.builder()
                .code("A-1070").description("Description: ...")
                .dateTime(OffsetDateTime.now()).build();
    }

    @Test
    void saveHopArrivalEntityTest() {
        HopArrivalEntity hopArrivalEntityTest = repo.save(hopArrivalEntity);
        assertNotNull(hopArrivalEntityTest.getId());
        assertEquals(hopArrivalEntityTest.getCode(), hopArrivalEntity.getCode());
        assertEquals(hopArrivalEntityTest.getDescription(), hopArrivalEntity.getDescription());
    }

}