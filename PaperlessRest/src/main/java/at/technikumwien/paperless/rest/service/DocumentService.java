package at.technikumwien.paperless.rest.service;


import at.technikumwien.paperless.rest.repository.jpa.DocumentJpa;
import at.technikumwien.paperless.rest.DocumentController;


import at.technikumwien.paperless.rest.repository.jpa.DocumentJpaRepository;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    private final DocumentJpaRepository documentJpaRepository;

    public DocumentService(DocumentJpaRepository documentJpaRepository) {
        this.documentJpaRepository = documentJpaRepository;
    }

    public DocumentJpa saveDocument(MultipartFile file) throws IOException {
        DocumentJpa documentJpa = new DocumentJpa();
        documentJpa.setFileName(file.getOriginalFilename());
        documentJpa.setData(file.getBytes());

        logger.info("document saved!");

        documentJpaRepository.save(documentJpa);
        return documentJpa;
    }

    public List<String> getAllDocumentNames() {
        return documentJpaRepository.findAll()
                .stream()
                .map(DocumentJpa::getFileName)
                .collect(Collectors.toList());
    }
}
