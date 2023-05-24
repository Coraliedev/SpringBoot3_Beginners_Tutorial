package contentcalendar.controller;

import contentcalendar.model.Content;
import contentcalendar.repository.ContentCollectionRepository;
import contentcalendar.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/content")
@CrossOrigin
public class ContentController {
//    private final ContentCollectionRepository repository;
    private final ContentJdbcTemplateRepository repository;

    public ContentController(ContentJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.getAllContent();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.getContent(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        repository.createContent(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Content content) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        System.out.println(content);
        repository.updateContent(content, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteContent(id);
    }

}
