package cz.sda.store.cart;

public class ItemNotInCartException extends RuntimeException {

    public ItemNotInCartException(Long id) {
        super(String.format("Given id %d is not in cart", id));
    }
}
