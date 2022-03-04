package cz.sda.store.web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StoreItem {

    @Id
    private Long id;
    private String name;

    @ManyToOne
    private StoreGroup storeGroup;
}
