package at.technikum.paperless.ocrworker;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaperlessOcrWorkerApplication.class)
@ActiveProfiles("test")
class PaperlessOcrWorkerApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(PaperlessOcrWorkerApplicationTests.class);


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
