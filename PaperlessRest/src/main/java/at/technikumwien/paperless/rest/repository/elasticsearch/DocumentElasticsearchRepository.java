package at.technikumwien.paperless.rest.repository.elasticsearch;

import at.technikumwien.paperless.rest.Document;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DocumentElasticsearchRepository extends ElasticsearchRepository<Document, Long> {
    // Custom query to search in the textContent field
    @Query("{ \"match\": { \"dataString\": \"?0\" } }")

    List<Document> findByTextContentContaining(String searchedDataString);
}
