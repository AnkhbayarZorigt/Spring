package cz.sda.store.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> findByCategory(Long categoryId) {
        return bookRepository.findByCategoryWithQuery(categoryId).stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    public List<BookDto> findBy(String lang) {
        return bookRepository.findAllByLanguage(lang).stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    public List<BookDto> findBy(String lang, Integer year) {
        return bookRepository.findAllByLanguageAndYear(lang, year).stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
}
