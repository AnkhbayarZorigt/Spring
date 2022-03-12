package cz.sda.store.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private static final String URI = "/api/web";
    private static final String URI_OVERVIEW = URI + "/all";
    private static final String URI_BY_GROUP = URI + "/{groupId}";
    private static final String URI_BY_FILTER = URI + "/filter";

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final StoreService service;


    @GetMapping(URI_OVERVIEW)
    public Iterable<BookDto> getAllItems() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(URI_BY_GROUP)
    public List<BookDto> getByGroup(@PathVariable Long groupId) {
        return service.findByCategory(groupId);
    }

    @GetMapping(URI_BY_FILTER)
    public List<BookDto> getbyLang(@RequestParam(required = false) String lang, @RequestParam(required = false) Integer year) {
        if (Objects.nonNull(year)) {
            return service.findBy(lang, year);
        } else {
            return service.findBy(lang);
        }
    }
}
