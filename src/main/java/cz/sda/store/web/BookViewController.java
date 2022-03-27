package cz.sda.store.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookViewController {
    private static final String URI_BOOK = "/book";

    private final HttpSession httpSession;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @GetMapping(URI_BOOK)
    public ModelAndView displayBook() {
        ModelAndView mv = new ModelAndView("book");
        var bookDtoList = bookRepository.findAll().stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
        mv.addObject("bookList", bookDtoList);
        mv.addObject("h2Text", "A form");
        return mv;
    }
}
