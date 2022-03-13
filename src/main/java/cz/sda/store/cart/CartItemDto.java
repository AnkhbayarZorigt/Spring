package cz.sda.store.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer vat;
    private Integer priceIncludingVat;
}
