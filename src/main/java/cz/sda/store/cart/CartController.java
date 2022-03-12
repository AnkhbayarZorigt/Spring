package cz.sda.store.cart;

import cz.sda.store.web.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartController {
    private static final String URI = "/api/cart";
    private static final String URI_ALL = URI + "/all";
    private static final String URI_ADD = URI + "/add";
    private static final String URI_REMOVE = URI + "/remove";
    private static final String URI_REMOVE_ALL = URI + "/remove-all";

    private final CartService cartService;

    @GetMapping(URI_ALL)
    public List<CartItem> getAll() {
        return cartService.findAll();
    }

    @DeleteMapping(URI_REMOVE + "/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping(URI_REMOVE_ALL + "/{cartId}")
    public ResponseEntity<String> removeAll(@PathVariable Long cartId) {
        cartService.removeAll(cartId);
        return ResponseEntity.ok("Item removed");
    }

    @PostMapping(URI_ADD + "/{cartId}")
    public CartDto addItem(@PathVariable Long cartId, @RequestBody BookDto bookDto) {
        return cartService.addItem(cartId, bookDto);
    }
}
