package cz.sda.store.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Book")
@Setter
@Getter
@NoArgsConstructor
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
