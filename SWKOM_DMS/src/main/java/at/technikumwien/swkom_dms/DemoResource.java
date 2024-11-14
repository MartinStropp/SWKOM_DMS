package at.technikumwien.swkom_dms;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
public class DemoResource {
    @GetMapping("/")
    public ResponseEntity<String> Test() {
        return ResponseEntity.ok("Hello World");
    }

}
