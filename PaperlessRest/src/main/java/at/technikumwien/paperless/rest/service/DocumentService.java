package at.technikumwien.paperless.rest.service;

import at.technikumwien.paperless.rest.repository.elasticsearch.DocumentElasticsearch;
import at.technikumwien.paperless.rest.DocumentJpa;
import at.technikumwien.paperless.rest.DocumentController;

import at.technikumwien.paperless.rest.repository.elasticsearch.DocumentElasticsearchRepository;
import at.technikumwien.paperless.rest.repository.jpa.DocumentJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    public DocumentJpa saveDocument(MultipartFile file) throws IOException {
        DocumentJpa documentJpa = new DocumentJpa();
        documentJpa.setFileName(file.getOriginalFilename());
        documentJpa.setData(file.getBytes());

        documentJpaRepository.save(documentJpa);
        return documentJpa;
    }

    public List<String> getAllDocumentNames() {
        return documentJpaRepository.findAll()
                .stream()
                .map(DocumentJpa::getFileName)
                .collect(Collectors.toList());
    }

    public List<DocumentElasticsearch> getSearchedDocuments(String searchedText) {
       List<DocumentElasticsearch> list = new ArrayList<>();
//       documentElasticsearchRepository.findByTextContentContaining(searchedText).
//               findEach(list::add);

       documentElasticsearchRepository.findAll()
               .forEach(list::add);
       return list;
    }
}
