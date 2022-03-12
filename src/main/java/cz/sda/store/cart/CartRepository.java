package cz.sda.store.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    void removeByCartItemName(String name);

    void removeById(Long id);
}
