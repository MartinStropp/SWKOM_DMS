package at.technikumwien.paperless.rest.repository;

import at.technikumwien.paperless.rest.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}

