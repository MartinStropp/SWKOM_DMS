package at.technikum.paperless.ocrworker.elasticsearch;

import at.technikum.paperless.ocrworker.PaperlessDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DocumentElasticsearchRepository extends ElasticsearchRepository<PaperlessDocument, Long> {
}
