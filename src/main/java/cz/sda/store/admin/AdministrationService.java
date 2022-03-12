package cz.sda.store.admin;

import cz.sda.store.web.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdministrationService {

    private final CategoryRepository groupRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> findAllItems() {
        log.debug("Displaying all items in a shop");
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public Long saveNewGroup(Category category) {
        log.debug("Saving a new group with a name [{}]", category.getName());
        return groupRepository.save(category).getId();
    }

    public Long saveNewItem(BookDto bookDto) {
        log.debug("Saving new item with a name [{}]", bookDto.getTitle());
        var newStoreItem = bookMapper.fromDto(bookDto);
        return bookRepository.save(newStoreItem).getId();
    }

    public void deleteGroup(Long id) {
        log.debug("Removing Group with id {}", id);
        groupRepository.deleteById(id);
    }

    public void deleteItem(Long id) {
        log.debug("Removing item with id {}", id);
        bookRepository.deleteById(id);
    }
}
