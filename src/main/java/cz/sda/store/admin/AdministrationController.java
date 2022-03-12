package cz.sda.store.admin;

import cz.sda.store.web.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdministrationController {
    private static final String URI = "/api/web/administration";
    static final String URI_OVERVIEW = URI + "/all";
    static final String GROUP_URI = URI + "/group";
    static final String ITEM_URI = URI + "/item";

    private final AdministrationService service;

    @GetMapping(URI_OVERVIEW)
    public List<BookDto> getAllItems() {
        return service.findAllItems();
    }

    @PostMapping(GROUP_URI)
    public Long addNewGroup(@RequestBody Category newGroup) {
        return service.saveNewGroup(newGroup);
    }

    @PostMapping(ITEM_URI)
    public Long addNewItem(@RequestBody BookDto storeItem) {
        return service.saveNewItem(storeItem);
    }

    @DeleteMapping(GROUP_URI + "/{groupId}")
    public void removeGroup(@PathVariable Long groupId) {
       service.deleteGroup(groupId);
    }

    @DeleteMapping(ITEM_URI + "/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        service.deleteItem(itemId);
    }
}
