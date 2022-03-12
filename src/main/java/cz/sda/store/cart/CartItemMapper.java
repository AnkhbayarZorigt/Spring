package cz.sda.store.cart;

import cz.sda.store.web.BookDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartItemMapper {


    @Mapping(source = "title", target = "name")
    @Mapping(target = "vat", constant = "0.15")
    @Mapping(target = "price", defaultValue = "300")
    CartItem fromBook(BookDto dto);
}
