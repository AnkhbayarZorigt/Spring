package cz.sda.store.cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {
    @Id
    private Long id;
    private Integer totalSum;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItem;


    private List<CartItem> getSafelyCartItem() {
        return Objects.nonNull(cartItem) ? cartItem : List.of();
    }

    @PrePersist
    public void updatePrice() {
        totalSum = getSafelyCartItem().stream().mapToInt(CartItem::getPriceIncludingVat).reduce(Integer::sum).orElse(0);
    }
}
