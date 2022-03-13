package cz.sda.store.cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {

    @Id
    private Long id;
    private String name;
    private Integer price;
    private Integer vat;
    private Integer priceIncludingVat;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    @PrePersist
    public void setVat() {
        priceIncludingVat = Math.multiplyExact(price, 1 + (vat/100));
    }
}
