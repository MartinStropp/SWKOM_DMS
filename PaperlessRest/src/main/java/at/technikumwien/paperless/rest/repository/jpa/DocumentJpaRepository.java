package at.technikumwien.paperless.rest.repository.jpa;

import at.technikumwien.paperless.rest.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentJpaRepository extends JpaRepository<Document, Long> {
}
