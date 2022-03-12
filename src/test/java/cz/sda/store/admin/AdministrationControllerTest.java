package cz.sda.store.admin;

import cz.sda.store.web.BookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdministrationController.class)
@ExtendWith(SpringExtension.class)
@ComponentScan("cz.sda.store.admin")
class AdministrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdministrationService administrationService;


    @BeforeEach
    void prepareForTest() {
        List<BookDto> resultList = List.of(storeItem(1L, "Item 1"), storeItem(2L, "Item 2"));
        when(administrationService.findAllItems()).thenReturn(resultList);
    }

    private BookDto storeItem(Long id, String name) {
        return BookDto.builder().id(id).name(name).build();
    }


    @Test
    @DisplayName("Try to load the page")
    void loadPage() throws Exception {
        mockMvc.perform(get(AdministrationController.URI_OVERVIEW))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}