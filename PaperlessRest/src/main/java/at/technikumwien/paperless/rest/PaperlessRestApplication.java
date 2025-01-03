package at.technikumwien.paperless.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PaperlessRestApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PaperlessRestApplication.class, args);
    }

    // Enable CORS globally for the application
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // CORS für alle Endpunkte aktivieren
                .allowedOrigins("*")  // Anfragen von allen Ursprüngen zulassen
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false);  // Muss auf false gesetzt sein, wenn allowedOrigins auf "*" steht
    }
}
