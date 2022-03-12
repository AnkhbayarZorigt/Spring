package cz.sda.store.cart;

import cz.sda.store.web.Book;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Integer totalSum;
    private Integer vat;
    private Integer sumIncludingVat;
    private List<Book> purchased;
}
