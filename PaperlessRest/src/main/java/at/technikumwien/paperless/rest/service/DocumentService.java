package at.technikumwien.paperless.rest.service;

import at.technikumwien.paperless.rest.Document;
import at.technikumwien.paperless.rest.DocumentController;
import at.technikumwien.paperless.rest.repository.elasticsearch.DocumentElasticsearchRepository;
import at.technikumwien.paperless.rest.repository.jpa.DocumentJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);
    private final DocumentJpaRepository documentJpaRepository;
    private final DocumentElasticsearchRepository documentElasticsearchRepository;

    public DocumentService(DocumentJpaRepository documentJpaRepository, DocumentElasticsearchRepository documentElasticsearchRepository) {
        this.documentJpaRepository = documentJpaRepository;
        this.documentElasticsearchRepository = documentElasticsearchRepository;
    }

    public Document saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setData(file.getBytes());

        documentJpaRepository.save(document);
        return document;
    }

    public List<String> getAllDocumentNames() {
        return documentJpaRepository.findAll()
                .stream()
                .map(Document::getFileName)
                .collect(Collectors.toList());
    }

    public List<Document> getSearchedDocuments(String searchedText) {
//        return documentElasticsearchRepository.findByTextContentContaining(searchedText);
        logger.info("test1");
        return (List<Document>) documentElasticsearchRepository.findAll();
    }
}
