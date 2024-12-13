package at.technikumwien.paperless.rest;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class PaperlessRestApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(PaperlessRestApplicationTests.class);


    @Container
    protected static ElasticsearchContainer elasticsearchContainer = new ElasticTestContainer();

    @BeforeAll
    static void setUp() {
        elasticsearchContainer.start();

        logger.info("Starting Tests");
    }

    @AfterAll
    static void destroy() {
        elasticsearchContainer.stop();
        logger.info("Stopping Tests");
    }

    @Test
    void contextLoads() {
        logger.info("Test 1");
    }
}
