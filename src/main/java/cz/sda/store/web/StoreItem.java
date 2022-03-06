package cz.sda.store.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreItem {

    @Id
    private Long id;
    private String name;

    @ManyToOne
    private StoreGroup storeGroup;
}
