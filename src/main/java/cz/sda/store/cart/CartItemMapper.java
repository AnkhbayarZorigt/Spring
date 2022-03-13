package cz.sda.store.cart;

import cz.sda.store.web.BookDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartItemMapper {


    @Mapping(source = "title", target = "name")
    @Mapping(target = "vat", constant = "15")
    @Mapping(target = "price", constant = "300")
    @Mapping(target = "priceIncludingVat", ignore = true)
    //Add before persist
    CartItem fromBook(BookDto dto);

    @Mapping(target = "id")
    @Mapping(target = "name")
    @Mapping(target = "vat")
    @Mapping(target = "price")
    @Mapping(target = "priceIncludingVat")
    CartItemDto toDto(CartItem dto);
}
