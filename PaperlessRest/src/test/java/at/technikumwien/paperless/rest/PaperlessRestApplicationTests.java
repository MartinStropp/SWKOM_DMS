package at.technikumwien.paperless.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest // Specify the main application class
@ActiveProfiles("test")
public class PaperlessRestApplicationTests {

    @Test
    void contextLoads() {
        // Test that the application context loads successfully
    }
}

