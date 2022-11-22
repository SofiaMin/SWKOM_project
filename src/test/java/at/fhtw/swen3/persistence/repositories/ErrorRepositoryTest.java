package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ErrorRepositoryTest {
    @Autowired
    private ErrorRepository repo;

    ErrorEntity errorEntity;

    @BeforeEach
    void setUp() {
        errorEntity = ErrorEntity.builder()
                .errorMessage("This is an error message").build();
    }

    @Test
    void saveErrorEntityTest() {
        ErrorEntity errorEntityTest = repo.save(errorEntity);
        assertEquals(errorEntity.getErrorMessage(), errorEntityTest.getErrorMessage());
    }


}