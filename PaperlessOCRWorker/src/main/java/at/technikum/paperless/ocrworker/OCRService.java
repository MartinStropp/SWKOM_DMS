package at.technikum.paperless.ocrworker;

import at.technikum.paperless.ocrworker.elasticsearch.DocumentElasticsearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OCRService {

    private static final Logger logger = LoggerFactory.getLogger(OCRService.class);

    private final RabbitTemplate rabbitTemplate;
    private final DocumentElasticsearchRepository documentElasticsearchRepository;

    public OCRService(RabbitTemplate rabbitTemplate, DocumentElasticsearchRepository documentElasticsearchRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.documentElasticsearchRepository = documentElasticsearchRepository;
    }

    public String processOCR(PaperlessDocument paperlessDocument) {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("/usr/share/tessdata"); // Path inside the container
        tesseract.setLanguage("eng");

        try {
            Path destination = Path.of(paperlessDocument.getFileName()); // Adjust filename and extension as needed
            Files.write(destination, paperlessDocument.getData());
            return tesseract.doOCR(new File(destination.toAbsolutePath().toString()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @RabbitListener(queues = "ocr_queue")
    public void onMessageReceived(String message) {
        try {
            logger.info("Received message");
            // Erwartet, dass message den Pfad zum Bild enthält
            PaperlessDocument paperlessDocument = new ObjectMapper().readValue(message, PaperlessDocument.class);
            paperlessDocument.setDataString(processOCR(paperlessDocument));
            // Weiterverarbeitung oder Speicherung des OCR-Ergebnisses
            sendToResultQueue(paperlessDocument);
            documentElasticsearchRepository.save(paperlessDocument);
            logger.info(paperlessDocument.getDataString());
        } catch (Exception e) {
            logger.error("error in onMessageRecived: {}", e.getMessage());
        }
    }

    public void sendToResultQueue(PaperlessDocument paperlessDocument) {
        try {
            // Send document data as a message to the OCR queue
            Message message = MessageBuilder.withBody(paperlessDocument.toJsonString().getBytes(StandardCharsets.UTF_8))
                    .setContentType("application/json")
                    .build();
            logger.info("Sending message to Result queue");
            rabbitTemplate.convertAndSend("result_queue", message);
        } catch (Exception e) {
            logger.error("error in sendToResultQueue: {}", e.getMessage());
        }

    }
}

