package at.technikumwien.swkom_dms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest // Specify the main application class
@ActiveProfiles("test")
public class SwkomDmsApplicationTests {

    @Test
    void contextLoads() {
        // Test that the application context loads successfully
    }
}

