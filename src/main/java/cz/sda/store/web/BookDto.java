package cz.sda.store.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @SuppressWarnings({"unused"}) //use in Thymeleaf
    public String detectPeriod() {
        if (year < 1000) {
            return "Very old";
        } else if (year < 2000) {
            return "not so old";
        } else {
            return "quiet new";
        }
    }
}
