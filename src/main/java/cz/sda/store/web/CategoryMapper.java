package cz.sda.store.web;

import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {

    String TO_DTO = "toDto";
    String FROM_DTO = "fromDto";

    @Named(TO_DTO)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "name")
    CategoryDto toDto(Category entity);

    @Named(FROM_DTO)
    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "toDto")
    Category fromDto(CategoryDto dto);
}
