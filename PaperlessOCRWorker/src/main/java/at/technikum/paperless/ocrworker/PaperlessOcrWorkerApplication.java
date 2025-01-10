package at.technikum.paperless.ocrworker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaperlessOcrWorkerApplication{

    private final static Logger logger = LoggerFactory.getLogger(PaperlessOcrWorkerApplication.class);

    public static void main(String[] args) {

        logger.info("Application started");

        SpringApplication.run(PaperlessOcrWorkerApplication.class, args);
    }
}
