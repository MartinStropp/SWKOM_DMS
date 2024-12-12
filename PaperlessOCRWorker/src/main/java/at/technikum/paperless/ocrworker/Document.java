package at.technikum.paperless.ocrworker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Document {
    private Long id;
    private String fileName;
    private byte[] data;
    private String dataString;

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    // Manuelle Getter
    // public Long getId() {
    //     return id;
    // }
    //
    // public String getFileName() {
    //     return fileName;
    // }
    //
    // public byte[] getData() {
    //     return data;
    // }
    //
    // public String getDataString() {
    //     return dataString;
    // }
    //
    // // Manuelle Setter
    // public void setId(Long id) {
    //     this.id = id;
    // }
    //
    // public void setFileName(String fileName) {
    //     this.fileName = fileName;
    // }
    //
    // public void setData(byte[] data) {
    //     this.data = data;
    // }
    //
    // public void setDataString(String dataString) {
    //     this.dataString = dataString;
    // }
}
