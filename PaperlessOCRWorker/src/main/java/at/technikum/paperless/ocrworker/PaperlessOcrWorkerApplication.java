package at.technikum.paperless.ocrworker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaperlessOcrWorkerApplication{

    public static void main(String[] args) {

        SpringApplication.run(PaperlessOcrWorkerApplication.class, args);
    }
}
