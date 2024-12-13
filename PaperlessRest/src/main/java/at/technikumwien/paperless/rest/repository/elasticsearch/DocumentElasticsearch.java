package at.technikumwien.paperless.rest.repository.elasticsearch;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "document")
public class DocumentElasticsearch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // oder GenerationType.AUTO
    private Long id;
    private String fileName;
    private String dataString;
}
