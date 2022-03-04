package cz.sda.store.web;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "item_group")
@Setter
@Getter
public class StoreGroup {

    @Id
    private Long id;
    private String name;

    @OneToMany
    private List<StoreItem> storeGroupList;
}
