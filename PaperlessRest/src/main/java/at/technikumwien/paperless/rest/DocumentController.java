package at.technikumwien.paperless.rest;

import at.technikumwien.paperless.rest.repository.jpa.DocumentJpa;
import at.technikumwien.paperless.rest.service.DocumentService;
import at.technikumwien.paperless.rest.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final RabbitMqService rabbitMqService;
    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);


    public DocumentController(DocumentService documentService, RabbitMqService rabbitMqService) {
        this.documentService = documentService;
        this.rabbitMqService = rabbitMqService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("upload new File");
            DocumentJpa documentJpa = documentService.saveDocument(file);
            logger.info("file in db gespeichert");
            rabbitMqService.sendToOcrQueue(documentJpa.toJsonString().getBytes(StandardCharsets.UTF_8));
            logger.info("file in elasticsearch gespeichert");
            logger.info("file upload erfolgreich");
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

//    @GetMapping("/search")
//    public List<String> getSearchedDocuments(@RequestParam("searchedText") String searchedText) {
//        logger.info("Suchanfrage: \"{}\"", searchedText);
//        List<String> documentNames = new ArrayList<>();
//        documentNames.add("Test daten");
//        logger.info("test daten added");
//        try {
//            List<PaperlessDocument> paperlessDocuments = documentService.getSearchedDocuments(searchedText);
//            documentNames = new ArrayList<>();
//            for (PaperlessDocument doc : paperlessDocuments) {
//                documentNames.add(doc.getFileName());
//            }
//            logger.info(documentNames.toString());
//        } catch (Exception e) {
//            logger.error("Error: {}", e.getMessage());
//
//        }
//            return documentNames;
//    }

    @GetMapping
    public List<String> getDocuments() {
        logger.info("GET Anfrage");
        return documentService.getAllDocumentNames();
    }
}

