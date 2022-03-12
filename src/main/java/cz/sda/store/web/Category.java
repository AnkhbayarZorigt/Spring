package cz.sda.store.web;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> storeGroupList;
}
