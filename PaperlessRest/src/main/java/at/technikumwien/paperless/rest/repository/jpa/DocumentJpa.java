package at.technikumwien.paperless.rest.repository.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
public class DocumentJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // oder GenerationType.AUTO
    private Long id;
    private String fileName;
    private byte[] data;
    private String dataString;

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    // Manuelle Getter
    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public String getDataString() {
        return dataString;
    }

    // Manuelle Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}
