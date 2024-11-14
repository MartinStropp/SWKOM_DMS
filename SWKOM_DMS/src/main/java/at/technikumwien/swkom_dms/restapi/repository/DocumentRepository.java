package at.technikumwien.swkom_dms.restapi.repository;

import at.technikumwien.swkom_dms.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}

