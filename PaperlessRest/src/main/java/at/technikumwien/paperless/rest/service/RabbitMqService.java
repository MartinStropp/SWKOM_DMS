package at.technikumwien.paperless.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);

    public RabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToOcrQueue(byte[] documentJsonBytes) {
        // Send document data as a message to the OCR queue
        Message message = MessageBuilder.withBody(documentJsonBytes)
                .setContentType("application/json")
                .build();
        logger.info("Sending message to OCR queue");
        rabbitTemplate.convertAndSend("ocr_queue", message);
    }
}
