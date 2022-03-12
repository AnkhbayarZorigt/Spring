package cz.sda.store.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String title;
    private String author;
    private String country;
    private String language;
    private Integer pages;
    private Integer year;

    @ManyToOne
    private Category category;
}
