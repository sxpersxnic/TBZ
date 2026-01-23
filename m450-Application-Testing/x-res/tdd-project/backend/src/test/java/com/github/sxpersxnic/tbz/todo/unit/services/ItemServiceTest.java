package com.github.sxpersxnic.tbz.todo.unit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.sxpersxnic.tbz.todo.items.Item;
import com.github.sxpersxnic.tbz.todo.items.ItemRepository;
import com.github.sxpersxnic.tbz.todo.items.ItemService;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item testItem;
    private UUID testId;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();
        testItem = new Item();
        testItem.setId(testId);
        testItem.setTitle("Test Item");
        testItem.setDescription("Test Description");
        testItem.setCompleted(false);
        testItem.setTags(new HashSet<>(Set.of("tag1", "tag2")));
        testItem.setAssignedUserId(UUID.fromString("11111111-1111-4111-a111-111111111111"));
    }

    // ==================== findAll ====================

    @Test
    void findAll_shouldReturnAllItems() {
        // Given
        Item item2 = new Item();
        item2.setId(UUID.randomUUID());
        item2.setTitle("Item 2");
        item2.setDescription("Description 2");
        item2.setCompleted(true);
        item2.setTags(new HashSet<>(Set.of("tag3", "tag4")));
        item2.setAssignedUserId(UUID.fromString("11111111-1111-4111-a111-111111111111"));

        List<Item> items = List.of(testItem, item2);
        when(itemRepository.findAll()).thenReturn(items);

        // When
        List<Item> result = itemService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(result.get(0).isCompleted());
        assertTrue(result.get(1).isCompleted());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void findAll_shouldReturnEmptyList_whenNoItemsExist() {
        // Given
        when(itemRepository.findAll()).thenReturn(List.of());

        // When
        List<Item> result = itemService.findAll();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(itemRepository, times(1)).findAll();
    }

    // ==================== findById ====================

    @Test
    void findById_shouldReturnItem_whenItemExists() {
        // Given
        when(itemRepository.findById(testId)).thenReturn(Optional.of(testItem));

        // When
        Optional<Item> result = itemService.findById(testId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testItem.getId(), result.get().getId());
        assertEquals(testItem.getTitle(), result.get().getTitle());
        assertEquals(testItem.isCompleted(), result.get().isCompleted());
        assertEquals(testItem.getDescription(), result.get().getDescription());
        assertEquals(testItem.getTags(), result.get().getTags());
        verify(itemRepository, times(1)).findById(testId);
    }

    @Test
    void findById_shouldReturnEmpty_whenItemDoesNotExist() {
        // Given
        UUID nonExistingId = UUID.randomUUID();
        when(itemRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // When
        Optional<Item> result = itemService.findById(nonExistingId);

        // Then
        assertTrue(result.isEmpty());
        verify(itemRepository, times(1)).findById(nonExistingId);
    }

    // ==================== create ====================

    @Test
    void create_shouldSaveAndReturnItem() {
        // Given
        Item newItem = new Item();
        newItem.setId(UUID.randomUUID());
        newItem.setTitle("New Item");
        newItem.setDescription("New Description");
        newItem.setCompleted(true);
        newItem.setTags(new HashSet<>(Set.of("tagA", "tagB")));
        newItem.setAssignedUserId(UUID.fromString("11111111-1111-4111-a111-111111111111"));

        Item savedItem = new Item();
        savedItem.setId(UUID.randomUUID());
        savedItem.setTitle(newItem.getTitle());
        savedItem.setDescription(newItem.getDescription());
        savedItem.setCompleted(newItem.isCompleted());
        savedItem.setTags(newItem.getTags());
        savedItem.setAssignedUserId(newItem.getAssignedUserId());

        when(itemRepository.save(newItem)).thenReturn(savedItem);

        // When
        Item result = itemService.create(newItem);

        // Then
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(newItem.getTitle(), result.getTitle());
        assertEquals(newItem.getDescription(), result.getDescription());
        assertTrue(result.isCompleted());
        assertEquals(newItem.getTags(), result.getTags());
        assertEquals(newItem.getAssignedUserId(), result.getAssignedUserId());
        verify(itemRepository, times(1)).save(newItem);
    }

    // ==================== update ====================

    @Test
    void update_shouldUpdateAndReturnItem_whenItemExists() {
        // Given
        Item updatedData = new Item();
        updatedData.setTitle("Updated Title");
        updatedData.setDescription("Updated Description");
        updatedData.setCompleted(true);
        updatedData.setTags(new HashSet<>(Set.of("updatedTag1", "updatedTag2")));
        updatedData.setAssignedUserId(UUID.randomUUID());

        Item updatedItem = new Item();
        updatedItem.setId(testId);
        updatedItem.setTitle(updatedData.getTitle());
        updatedItem.setDescription(updatedData.getDescription());
        updatedItem.setCompleted(updatedData.isCompleted());
        updatedItem.setTags(updatedData.getTags());
        updatedItem.setAssignedUserId(updatedData.getAssignedUserId());

        when(itemRepository.findById(testId)).thenReturn(Optional.of(testItem));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        // When
        Optional<Item> result = itemService.update(testId, updatedData);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testId, result.get().getId());
        assertEquals(updatedData.getTitle(), result.get().getTitle());
        assertEquals(updatedData.getDescription(), result.get().getDescription());
        assertTrue(result.get().isCompleted());
        assertEquals(updatedData.getAssignedUserId(), result.get().getAssignedUserId());
        verify(itemRepository, times(1)).findById(testId);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void update_shouldReturnEmpty_whenItemDoesNotExist() {
        // Given
        UUID nonExistingId = UUID.randomUUID();
        Item updatedData = new Item();
        updatedData.setTitle("Updated Title");
        updatedData.setCompleted(true);

        when(itemRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // When
        Optional<Item> result = itemService.update(nonExistingId, updatedData);

        // Then
        assertTrue(result.isEmpty());
        verify(itemRepository, times(1)).findById(nonExistingId);
        verify(itemRepository, never()).save(any(Item.class));
    }

    // ==================== delete ====================

    @Test
    void delete_shouldReturnTrue_whenItemExists() {
        // Given
        when(itemRepository.existsById(testId)).thenReturn(true);
        doNothing().when(itemRepository).deleteById(testId);

        // When
        boolean result = itemService.delete(testId);

        // Then
        assertTrue(result);
        verify(itemRepository, times(1)).existsById(testId);
        verify(itemRepository, times(1)).deleteById(testId);
    }

    @Test
    void delete_shouldReturnFalse_whenItemDoesNotExist() {
        // Given
        UUID nonExistingId = UUID.randomUUID();
        when(itemRepository.existsById(nonExistingId)).thenReturn(false);

        // When
        boolean result = itemService.delete(nonExistingId);

        // Then
        assertFalse(result);
        verify(itemRepository, times(1)).existsById(nonExistingId);
        verify(itemRepository, never()).deleteById(any());
    }
}
