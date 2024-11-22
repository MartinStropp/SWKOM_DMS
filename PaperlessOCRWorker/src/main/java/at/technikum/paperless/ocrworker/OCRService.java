package at.technikum.paperless.ocrworker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@Service
public class OCRService {

    private static final Logger logger = LoggerFactory.getLogger(OCRService.class);

    public String processOCR(String message) {
        // Beispiel für Tesseract OCR
        return message;
    }

    @RabbitListener(queues = "ocr_queue")
    public void onMessageReceived(String message) {
        logger.info("Received message");
        // Erwartet, dass message den Pfad zum Bild enthält
        String ocrResult = processOCR(message);
        // Weiterverarbeitung oder Speicherung des OCR-Ergebnisses
        logger.info(ocrResult);
    }
}

