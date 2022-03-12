package cz.sda.store.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.sda.store.web.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImportDataController {
    private static final String URI = "/api/import";
    private static final String URI_BOOK = URI + "/book";
    private static final String URI_GENRE = URI + "/genre";

    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Value("classpath:/data/books.json")
    private Resource booksJson;

    @Value("classpath:/data/genres.json")
    private Resource genreJson;

    @GetMapping(URI_BOOK)
    public ResponseEntity<String> importBook() throws IOException {
        log.debug("Import book has been called");
        BookDto[] books = objectMapper.readValue(booksJson.getInputStream(), BookDto[].class);
        var bookList = Arrays.stream(books)
                //convert BookDto into Book
                .map(bookMapper::fromDto)
                //Collect as java.util.List
                .collect(Collectors.toList());
        bookRepository.saveAll(bookList);

        log.debug("Saving data ends");
        return ResponseEntity.ok("Saved");
    }

    @GetMapping(URI_GENRE)
    public ResponseEntity<String> importGenre() throws IOException {
        log.debug("Import genre has been called");
        String[] genres = objectMapper.readValue(genreJson.getInputStream(), String[].class);
        var categoryList = Arrays.stream(genres)
                //convert String into Category
                .map(genreString -> {
                    var category = new Category();
                    category.setName(genreString);
                    return category;
                })
                //Collect as java.util.List
                .collect(Collectors.toList());
        categoryRepository.saveAll(categoryList);

        log.debug("Saving data ends");
        return ResponseEntity.ok("Saved");
    }
}
