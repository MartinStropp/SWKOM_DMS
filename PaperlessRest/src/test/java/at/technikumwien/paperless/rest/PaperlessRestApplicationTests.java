package at.technikumwien.paperless.rest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class PaperlessRestApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(PaperlessRestApplicationTests.class);


    @BeforeAll
    static void setUp() {

        logger.info("Starting Tests");
    }

    @AfterAll
    static void destroy() {

        logger.info("Stopping Tests");
    }

    @Test
    void contextLoads() {
        logger.info("Test 1");
    }
}
