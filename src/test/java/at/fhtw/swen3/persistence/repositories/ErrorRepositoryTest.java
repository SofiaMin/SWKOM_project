package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ErrorRepositoryTest {
    @Autowired
    private ErrorRepository errorRepository;
    private static ErrorEntity errorEntity;

    @BeforeAll
    static void setUp() {
        errorEntity = new ErrorEntity();
        errorEntity.setErrorMessage("My Error Message");
    }

    @Test
    public void saveErrorEntityTrue() {
        ErrorEntity error = errorRepository.save(errorEntity);
        assertEquals(error.getErrorMessage(), errorEntity.getErrorMessage());
    }

    @Test
    public void saveErrorEntityFalse() {
        ErrorEntity error = errorRepository.save(errorEntity);
        assertNotEquals(error.getId(), -1L);
    }
}
