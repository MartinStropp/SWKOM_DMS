package at.technikum.paperless.ocrworker;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DocumentElasticsearchRepository extends ElasticsearchRepository<Document, Long> {
}
