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

    public List<CartItem> findAll() {
        List<CartItem> itemList = new ArrayList<>();
        for (CartItem item : cartItemRepository.findAll()) {
            itemList.add(item);
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
        var cart = cartRepository.findById(id).orElse(new Cart());
        var cartItem = cartItemMapper.fromBook(dto);
        cart.addItem(cartItem);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }
}
