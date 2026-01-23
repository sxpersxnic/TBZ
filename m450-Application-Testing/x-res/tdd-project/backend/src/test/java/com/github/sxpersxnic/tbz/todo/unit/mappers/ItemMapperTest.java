package com.github.sxpersxnic.tbz.todo.unit.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.sxpersxnic.tbz.todo.items.Item;
import com.github.sxpersxnic.tbz.todo.items.ItemMapper;
import com.github.sxpersxnic.tbz.todo.items.ItemRequestDTO;
import com.github.sxpersxnic.tbz.todo.items.ItemResponseDTO;

class ItemMapperTest {

    private ItemMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = ItemMapper.INSTANCE;
    }

    // ==================== toDTO (single) ====================

    @Test
    void toDTO_shouldMapItemToResponseDTO() {
        // Given
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setTitle("Test Item");
        item.setDescription("Test Description");
        item.setCompleted(false);
        item.setDueDate(LocalDateTime.of(2026, 1, 31, 17, 0));

        // When
        ItemResponseDTO result = mapper.toDTO(item);

        // Then
        assertNotNull(result);
        assertEquals(item.getId(), result.getId());
        assertEquals(item.getTitle(), result.getTitle());
        assertEquals(item.getDescription(), result.getDescription());
        assertEquals(item.isCompleted(), result.isCompleted());
        assertEquals(item.getDueDate(), result.getDueDate());
    }

    @Test
    void toDTO_shouldReturnNull_whenItemIsNull() {
        // When
        ItemResponseDTO result = mapper.toDTO((Item) null);

        // Then
        assertNull(result);
    }

    @Test
    void toDTO_shouldMapItemWithNullDescription() {
        // Given
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setTitle("Test Item");
        item.setDescription(null);
        item.setCompleted(false);
        item.setDueDate(LocalDateTime.of(2026, 2, 1, 9, 0));

        // When
        ItemResponseDTO result = mapper.toDTO(item);

        // Then
        assertNotNull(result);
        assertEquals(item.getId(), result.getId());
        assertEquals(item.getTitle(), result.getTitle());
        assertNull(result.getDescription());
        assertEquals(result.isCompleted(), result.isCompleted());
        assertEquals(item.getDueDate(), result.getDueDate());
    }

    // ==================== toDTO (list) ====================

    @Test
    void toDTO_shouldMapListOfItemsToListOfResponseDTOs() {
        // Given
        Item item1 = new Item();
        item1.setId(UUID.randomUUID());
        item1.setTitle("Item 1");
        item1.setDescription("Description 1");
        item1.setCompleted(false);
        item1.setDueDate(LocalDateTime.of(2026, 1, 10, 12, 0));

        Item item2 = new Item();
        item2.setId(UUID.randomUUID());
        item2.setTitle("Item 2");
        item2.setDescription("Description 2");
        item2.setCompleted(false);
        item2.setDueDate(LocalDateTime.of(2026, 1, 20, 18, 30));

        List<Item> items = List.of(item1, item2);

        // When
        List<ItemResponseDTO> result = mapper.toDTO(items);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(item1.getId(), result.get(0).getId());
        assertEquals(item1.getTitle(), result.get(0).getTitle());
        assertEquals(item1.isCompleted(), result.get(0).isCompleted());
        assertEquals(item1.getDueDate(), result.get(0).getDueDate());

        assertEquals(item2.getId(), result.get(1).getId());
        assertEquals(item2.getTitle(), result.get(1).getTitle());
        assertEquals(item2.isCompleted(), result.get(1).isCompleted());
        assertEquals(item2.getDueDate(), result.get(1).getDueDate());
    }

    @Test
    void toDTO_shouldReturnNull_whenListIsNull() {
        // When
        List<ItemResponseDTO> result = mapper.toDTO((List<Item>) null);

        // Then
        assertNull(result);
    }

    @Test
    void toDTO_shouldReturnEmptyList_whenListIsEmpty() {
        // When
        List<ItemResponseDTO> result = mapper.toDTO(List.of());

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // ==================== fromDTO ====================

    @Test
    void fromDTO_shouldMapRequestDTOToItem() {
        // Given
        ItemRequestDTO requestDTO = new ItemRequestDTO();
        requestDTO.setTitle("New Item");
        requestDTO.setDescription("New Description");
        requestDTO.setCompleted(false);
        requestDTO.setDueDate(LocalDateTime.of(2026, 3, 5, 14, 0));

        // When
        Item result = mapper.fromDTO(requestDTO);

        // Then
        assertNotNull(result);
        assertNull(result.getId()); // ID should not be set from request
        assertEquals(requestDTO.getTitle(), result.getTitle());
        assertEquals(requestDTO.getDescription(), result.getDescription());
        assertEquals(requestDTO.isCompleted(), result.isCompleted());
        assertEquals(requestDTO.getDueDate(), result.getDueDate());
    }

    @Test
    void fromDTO_shouldReturnNull_whenRequestDTOIsNull() {
        // When
        Item result = mapper.fromDTO(null);

        // Then
        assertNull(result);
    }

    @Test
    void fromDTO_shouldMapRequestDTOWithNullDescription() {
        // Given
        ItemRequestDTO requestDTO = new ItemRequestDTO();
        requestDTO.setTitle("New Item");
        requestDTO.setDescription(null);
        requestDTO.setCompleted(false);
        requestDTO.setDueDate(LocalDateTime.of(2026, 4, 1, 8, 0));

        // When
        Item result = mapper.fromDTO(requestDTO);

        // Then
        assertNotNull(result);
        assertEquals(requestDTO.getTitle(), result.getTitle());
        assertNull(result.getDescription());
        assertEquals(result.isCompleted(), result.isCompleted());
        assertEquals(requestDTO.getDueDate(), result.getDueDate());
    }
}
