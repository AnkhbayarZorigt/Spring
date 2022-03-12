package cz.sda.store.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private static final String URI = "/api/web";
    private static final String URI_OVERVIEW = URI + "/all";
    private static final String URI_BY_GROUP = URI + "/{groupId}";

    private final StoreGroupRepository groupRepository;
    private final StoreItemRepository storeItemRepository;
    private final StoreItemMapper storeItemMapper;


    @GetMapping(URI_OVERVIEW)
    public Iterable<StoreItemDto> getAllItems() {
        return storeItemRepository.findAll().stream().map(storeItemMapper::toDto).collect(Collectors.toList());
    }


    @GetMapping(URI_BY_GROUP)
    public List<StoreItem> getByGroup(@PathVariable Long groupId) {
        return groupRepository.findById(groupId).get().getStoreGroupList();
    }
}
