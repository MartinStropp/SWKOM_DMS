package at.technikumwien.swkom_dms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RabbitMqService {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);

    public RabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToOcrQueue(String documentData) {
        // Send document data as a message to the OCR queue
        Message message = MessageBuilder.withBody(documentData.getBytes())
                .setContentType("text/plain")
                .build();
        rabbitTemplate.convertAndSend("ocr_queue", message);
        logger.info("Document sent to OCR queue: {}", documentData);
    }
}
