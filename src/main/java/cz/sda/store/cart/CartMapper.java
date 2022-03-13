package cz.sda.store.cart;

import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "totalSum")
    CartDto toDto(Cart entity);

    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "toDto")
    Cart fromDto(CartDto dto);
}
