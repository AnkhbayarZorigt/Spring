package cz.sda.store.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.sda.store.web.BookDto;
import cz.sda.store.web.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@ExtendWith(SpringExtension.class)
@ComponentScan("cz.sda.store.cart")
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private static CartItemDto cartItem(Long id, String name) {
        return CartItemDto.builder().
                id(id)
                .name(name)
                .price(100)
                .vat(15)
                .priceIncludingVat(115)
                .build();
    }

    private static CartDto cartDto() {
        return CartDto.builder()
                .id(1L)
                .totalSum(200)
                .build();
    }

    @Test
    @DisplayName("Show all items in cart")
    void getAll() throws Exception {
        List<CartItemDto> resultList = List.of(cartItem(1L, "Item 1"), cartItem(2L, "Item 2"));
        when(cartService.findAll()).thenReturn(resultList);
        mockMvc.perform(get(CartController.URI_ALL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("Add item to cart, which already exists")
    void addOneItemExistingOK() throws Exception {
        when(cartService.addItem(anyLong(), any())).thenReturn(cartDto());
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put(CartController.URI_ADD + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.totalSum").value(200));
    }

    @Test
    @DisplayName("Add first item to cart")
    void addOneItemNewOK() throws Exception {
        when(cartService.addItem(any())).thenReturn(cartDto());
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post(CartController.URI_ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.totalSum").value(200));
    }

    @Test
    @DisplayName("Add remove item from cart")
    void removeItem() throws Exception {
        mockMvc.perform(delete(CartController.URI_REMOVE + "/2"))
                .andExpect(status().isOk());
    }

    private static BookDto bookDto() {
        return BookDto.builder()
                .id(20L)
                .author("Author")
                .category(CategoryDto.builder().id(5L).build())
                .country("Country")
                .year(1980)
                .title("Title")
                .build();
    }
}