package cz.sda.store.cart;

import cz.sda.store.web.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartMapper cartMapper;

    public List<CartItemDto> findAll() {
        List<CartItemDto> itemList = new ArrayList<>();
        for (CartItem item : cartItemRepository.findAll()) {
            itemList.add(cartItemMapper.toDto(item));
        }
        return itemList;
    }

    public void removeItem(Long itemId) {
        var carItem = cartItemRepository.findById(itemId)
                .orElseThrow((() -> new ItemNotInCartException(itemId)));
        log.debug("Removing {}", carItem.getName());
        cartRepository.removeByCartItemName(carItem.getName());
    }

    public void removeAll(Long cartId) {
        cartRepository.removeById(cartId);
    }

    @Transactional
    public CartDto addItem(Long id, BookDto dto) {
        var cart = findCart(id);
        return addItemToCart(cart, dto);
    }

    @Transactional
    public CartDto addItem(BookDto dto) {
        return addItemToCart(new Cart(), dto);
    }

    private CartDto addItemToCart(Cart cart, BookDto dto) {
        var cartItem = cartItemMapper.fromBook(dto);
        cart.addItem(cartItem);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    private Cart findCart(Long id) {
        return cartRepository.findById(id).orElse(new Cart());
    }
}
