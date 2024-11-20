package at.technikumwien.swkom_dms.service;

import at.technikumwien.swkom_dms.Document;
import at.technikumwien.swkom_dms.repository.DocumentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;


    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public String saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setData(file.getBytes());

        documentRepository.save(document);
        return "File uploaded successfully";
    }

    public List<String> getAllDocumentNames() {
        return documentRepository.findAll()
                .stream()
                .map(Document::getFileName)
                .collect(Collectors.toList());
    }
}
