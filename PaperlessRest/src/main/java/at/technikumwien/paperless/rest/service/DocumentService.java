package at.technikumwien.paperless.rest.service;

import at.technikumwien.paperless.rest.Document;
import at.technikumwien.paperless.rest.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;


    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setData(file.getBytes());

        documentRepository.save(document);
        return document;
    }

    public List<String> getAllDocumentNames() {
        return documentRepository.findAll()
                .stream()
                .map(Document::getFileName)
                .collect(Collectors.toList());
    }
}
