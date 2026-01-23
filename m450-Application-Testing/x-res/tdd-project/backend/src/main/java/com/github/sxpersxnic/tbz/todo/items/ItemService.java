package com.github.sxpersxnic.tbz.todo.items;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> update(UUID id, Item updatedData) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setTitle(updatedData.getTitle());
                    existingItem.setDescription(updatedData.getDescription());
                    existingItem.setCompleted(updatedData.isCompleted());
                    existingItem.setAssignedUserId(updatedData.getAssignedUserId());
                    existingItem.setTags(updatedData.getTags());
                    return itemRepository.save(existingItem);
                });
    }

    public boolean delete(UUID id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
