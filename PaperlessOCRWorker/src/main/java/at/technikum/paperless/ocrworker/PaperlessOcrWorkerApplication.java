package at.technikum.paperless.ocrworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "at.technikum.paperless.ocrworker.elasticsearch")
@SpringBootApplication
public class PaperlessOcrWorkerApplication{

    public static void main(String[] args) {
        SpringApplication.run(PaperlessOcrWorkerApplication.class, args);
    }
}
