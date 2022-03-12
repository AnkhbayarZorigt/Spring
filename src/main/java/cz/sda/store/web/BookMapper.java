package cz.sda.store.web;

import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = CategoryMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "title")
    @Mapping(target = "author")
    @Mapping(target = "country")
    @Mapping(target = "language")
    @Mapping(target = "pages")
    @Mapping(target = "year")
    @Mapping(target = "category", qualifiedByName = CategoryMapper.TO_DTO)
    BookDto toDto(Book entity);

    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "category", qualifiedByName = CategoryMapper.FROM_DTO)
    Book fromDto(BookDto dto);
}
