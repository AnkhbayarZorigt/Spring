package cz.sda.store.admin;

import cz.sda.store.web.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdministrationService {

    private final StoreGroupRepository groupRepository;
    private final StoreItemRepository storeItemRepository;
    private final StoreItemMapper storeItemMapper;

    public List<StoreItemDto> findAllItems() {
        log.debug("Displaying all items in a shop");
        return storeItemRepository.findAll().stream().map(storeItemMapper::toDto).toList();
    }

    public Long saveNewGroup(StoreGroup storeGroup) {
        log.debug("Saving a new group with a name [{}]", storeGroup.getName());
        return groupRepository.save(storeGroup).getId();
    }

    public Long saveNewItem(StoreItemDto storeItemDto) {
        log.debug("Saving new item with a name [{}]", storeItemDto.getName());
        var newStoreItem = storeItemMapper.fromDto(storeItemDto);
        return storeItemRepository.save(newStoreItem).getId();
    }

    public void deleteGroup(Long id) {
        log.debug("Removing Group with id {}", id);
        groupRepository.deleteById(id);
    }

    public void deleteItem(Long id) {
        log.debug("Removing item with id {}", id);
        storeItemRepository.deleteById(id);
    }
}
