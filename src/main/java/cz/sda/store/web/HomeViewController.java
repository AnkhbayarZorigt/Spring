package cz.sda.store.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeViewController {
    public static final String ROOT_URI = "/store";

    @GetMapping("/home")
    public ModelAndView displayPage() {
        log.debug("Displaying hoome page");
        return new ModelAndView("home");
    }
}
