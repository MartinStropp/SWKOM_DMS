package at.technikumwien.paperless.rest.repository.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentElasticsearchRepository extends ElasticsearchRepository<DocumentElasticsearch, Long> {
    // Custom query to search in the textContent field
    @Query("{ \"match\": { \"dataString\": \"?0\" } }")
    List<DocumentElasticsearch> findByTextContentContaining(String searchedDataString);
}
