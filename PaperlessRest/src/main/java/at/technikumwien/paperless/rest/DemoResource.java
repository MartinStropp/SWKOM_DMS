package at.technikumwien.paperless.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
public class DemoResource {
    @GetMapping("/")
    public ResponseEntity<String> Test() {
        return ResponseEntity.ok("Hello World");
    }

}
