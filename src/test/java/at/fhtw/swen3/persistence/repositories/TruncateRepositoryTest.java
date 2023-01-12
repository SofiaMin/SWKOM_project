package at.fhtw.swen3.persistence.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class TruncateRepositoryTest {
    @Autowired
    private TruncateRepositoryImpl repositoty;

    @Test
    public void test() {
        repositoty.truncate();
    }
}
