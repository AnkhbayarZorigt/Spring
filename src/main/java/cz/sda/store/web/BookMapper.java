package cz.sda.store.web;

import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "id")
//    @Mapping(target = "name")
    BookDto toDto(Book entity);

    @BeanMapping(ignoreByDefault = true)
    @InheritInverseConfiguration(name = "toDto")
    Book fromDto(BookDto dto);
}
