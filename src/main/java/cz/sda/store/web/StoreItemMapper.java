package cz.sda.store.web;

import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StoreItemMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "name")
    StoreItemDto toDto(StoreItem entity);

    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "toDto")
    StoreItem fromDto(StoreItemDto dto);
}
