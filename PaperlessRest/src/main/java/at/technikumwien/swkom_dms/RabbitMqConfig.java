package at.technikumwien.swkom_dms;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // Define the queue where documents will be sent for OCR
    @Bean
    public Queue ocrQueue() {
        return new Queue("ocr_queue", false); // non-durable queue
    }

    @Bean
    public Queue resultQueue() {
        return new Queue("result_queue", false);
    }
}
