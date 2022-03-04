package cz.sda.store.cart;

import lombok.Data;
import cz.sda.store.web.StoreItem;

import java.util.List;

@Data
public class CartDto {
    private Integer totalSum;
    private Integer vat;
    private Integer sumIncludingVat;
    private List<StoreItem> purchased;
}
