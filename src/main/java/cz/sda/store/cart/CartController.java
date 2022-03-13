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
    static final String URI_ALL = URI + "/all";
    static final String URI_ADD = URI + "/add";
    static final String URI_REMOVE = URI + "/remove";
    static final String URI_REMOVE_ALL = URI + "/remove-all";

    private final CartService cartService;

    @GetMapping(URI_ALL)
    public List<CartItemDto> getAll() {
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

    @PutMapping(URI_ADD + "/{cartId}")
    public CartDto addItemToExisting(@PathVariable Long cartId, @RequestBody BookDto bookDto) {
        return cartService.addItem(cartId, bookDto);
    }

    @PostMapping(URI_ADD )
    public CartDto addItemTonew(@RequestBody BookDto bookDto) {
        return cartService.addItem(bookDto);
    }
}
