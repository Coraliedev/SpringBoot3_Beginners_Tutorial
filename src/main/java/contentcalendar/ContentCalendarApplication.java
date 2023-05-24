package contentcalendar;

import contentcalendar.model.Content;
import contentcalendar.model.Status;
import contentcalendar.model.Type;
import contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ContentCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCalendarApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ContentRepository repository) {
        return args -> {
            // insert some data into the database
            Content content = new Content(null, "My first Blog Post", "My first Blog Post", Status.IDEA, Type.VIDEO, LocalDateTime.now(), null, "");
            repository.save(content);
        };
    }
}
