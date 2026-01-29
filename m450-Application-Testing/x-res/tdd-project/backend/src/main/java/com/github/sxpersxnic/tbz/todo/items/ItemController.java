package com.github.sxpersxnic.tbz.todo.items;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok(itemMapper.toDTO(items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable UUID id) {
        return itemService.findById(id)
                .map(item -> ResponseEntity.ok(itemMapper.toDTO(item)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemRequestDTO requestDTO) {
        Item item = itemMapper.fromDTO(requestDTO);
        Item savedItem = itemService.create(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.toDTO(savedItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(
            @PathVariable UUID id,
            @Valid @RequestBody ItemRequestDTO requestDTO) {
        Item item = itemMapper.fromDTO(requestDTO);
        return itemService.update(id, item)
                .map(updatedItem -> ResponseEntity.ok(itemMapper.toDTO(updatedItem)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        if (itemService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
