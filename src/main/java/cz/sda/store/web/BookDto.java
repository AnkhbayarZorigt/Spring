package cz.sda.store.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "imageLink", "link" })
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String country;
    private String language;
    private Integer pages;
    private Integer year;
    private CategoryDto category;
}
