package at.technikum.paperless.ocrworker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.Doc;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class OCRService {

    private static final Logger logger = LoggerFactory.getLogger(OCRService.class);

    private final RabbitTemplate rabbitTemplate;

    public OCRService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String processOCR(Document document) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("/usr/share/tessdata"); // Path inside the container
        tesseract.setLanguage("deu");

        

        try {
            return tesseract.doOCR(new File(imagePath));
        } catch (TesseractException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @RabbitListener(queues = "ocr_queue")
    public void onMessageReceived(String message) {
        try {
            logger.info("Received message");
            // Erwartet, dass message den Pfad zum Bild enthält
            Document document = new ObjectMapper().readValue(message, Document.class);
            document.setDataString(processOCR(new String(document.getData(), StandardCharsets.UTF_8)));
            // Weiterverarbeitung oder Speicherung des OCR-Ergebnisses
            sendToResultQueue(document);
            logger.info(document.toJsonString());
        } catch (Exception e) {
            logger.error("error in onMessageRecived: {}", e.getMessage());
        }

    }

    public void sendToResultQueue(Document document) {
        try {
            // Send document data as a message to the OCR queue
            Message message = MessageBuilder.withBody(document.toJsonString().getBytes(StandardCharsets.UTF_8))
                    .setContentType("application/json")
                    .build();
            logger.info("Sending message to OCR queue");
            rabbitTemplate.convertAndSend("result_queue", message);
        } catch (Exception e) {
            logger.error("error in sendToResultQueue: {}", e.getMessage());
        }

    }
}

