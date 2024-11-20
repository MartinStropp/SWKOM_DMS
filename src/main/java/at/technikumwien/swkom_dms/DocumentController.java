package at.technikumwien.swkom_dms;

import at.technikumwien.swkom_dms.service.DocumentService;
import at.technikumwien.swkom_dms.service.RabbitMqService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final RabbitMqService rabbitMqService;


    @Autowired
    public DocumentController(DocumentService documentService , RabbitMqService rabbitMqService) {
        this.documentService = documentService;
        this.rabbitMqService = rabbitMqService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            String response = documentService.saveDocument(file);
            rabbitMqService.sendToOcrQueue(new String(file.getBytes(), "UTF-8"));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping
    public List<String> getDocuments() {
        return documentService.getAllDocumentNames();
    }
}

