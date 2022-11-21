package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.OffsetDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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