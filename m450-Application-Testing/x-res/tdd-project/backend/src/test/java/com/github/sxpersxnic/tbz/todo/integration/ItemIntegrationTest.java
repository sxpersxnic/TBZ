package com.github.sxpersxnic.tbz.todo.integration;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_PATH = "/api/items";
    private static final String EXISTING_ITEM_ID = "11111111-1111-1111-1111-111111111111";
    private static final String EXISTING_ITEM_TITLE = "Buy groceries";
    private static final String EXISTING_ITEM_DESCRIPTION = "Milk, eggs, bread, and butter";
    private static final Set<String> EXISTING_ITEM_TAGS = Set.of("shopping", "urgent");
    private static final String EXISTING_ITEM_ASSIGNED_USER_ID = "11111111-1111-4111-a111-111111111111";
    private static final String EXISTING_ITEM_PRIORITY = "HIGH";
    private static final String NON_EXISTING_ID = "99999999-9999-9999-9999-999999999999";

    // ==================== READ ====================

    @Test
    void getAllItems_shouldReturnListOfItems() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getItemById_shouldReturnItem() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/" + EXISTING_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(EXISTING_ITEM_ID))
                .andExpect(jsonPath("$.title").value(EXISTING_ITEM_TITLE))
                .andExpect(jsonPath("$.description").value(EXISTING_ITEM_DESCRIPTION))
                .andExpect(jsonPath("$.priority").value(EXISTING_ITEM_PRIORITY))
                .andExpect(jsonPath("$.tags", containsInAnyOrder(EXISTING_ITEM_TAGS.toArray())))
                .andExpect(jsonPath("$.assignedUserId").value(EXISTING_ITEM_ASSIGNED_USER_ID));
    }

    @Test
    void getItemById_shouldReturn404_whenItemDoesNotExist() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/" + NON_EXISTING_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // ==================== CREATE ====================

    @Test
    void createItem_shouldCreateAndReturnItem() throws Exception {
        String requestBody = """
                {
                    "title": "New test item",
                    "description": "This is a new test item",
                    "priority": "MEDIUM",
                    "assignedUserId": "11111111-1111-4111-a111-111111111111"
                }
                """;

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("New test item"))
                .andExpect(jsonPath("$.description").value("This is a new test item"))
                .andExpect(jsonPath("$.priority").value("MEDIUM"))
                .andExpect(jsonPath("$.assignedUserId").value(EXISTING_ITEM_ASSIGNED_USER_ID));
    }

    @Test
    void createItem_shouldReturn400_whenTitleIsBlank() throws Exception {
        String requestBody = """
                {
                    "title": "",
                    "description": "Description without title",
                    "assignedUserId": "11111111-1111-4111-a111-111111111111"
                }
                """;

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createItem_shouldReturn400_whenTitleExceedsMaxLength() throws Exception {
        String longTitle = "A".repeat(51);
        String requestBody = String.format("""
                {
                    "title": "%s",
                    "description": "Valid description"
                }
                """, longTitle);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createItem_shouldReturn409_whenTitleAlreadyExists() throws Exception {
        String requestBody = String.format("""
                {
                    "title": "%s",
                    "description": "Duplicate title test"
                }
                """, EXISTING_ITEM_TITLE);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());
    }

    // ==================== UPDATE ====================

    @Test
    void updateItem_shouldUpdateAndReturnItem() throws Exception {
        String requestBody = """
                {
                    "title": "Updated groceries list",
                    "description": "Updated description with more items",
                    "priority": "LOW",
                    "assignedUserId": "11111111-1111-4111-a111-111111111111"
                }
                """;

        mockMvc.perform(put(BASE_PATH + "/" + EXISTING_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(EXISTING_ITEM_ID))
                .andExpect(jsonPath("$.title").value("Updated groceries list"))
                .andExpect(jsonPath("$.description").value("Updated description with more items"))
                .andExpect(jsonPath("$.priority").value("LOW"))
                .andExpect(jsonPath("$.assignedUserId").value(EXISTING_ITEM_ASSIGNED_USER_ID));
    }

    @Test
    void updateItem_shouldReturn404_whenItemDoesNotExist() throws Exception {
        String requestBody = """
                {
                    "title": "Non-existing item update",
                    "description": "This should fail",
                    "assignedUserId": "11111111-1111-4111-a111-111111111111"
                }
                """;

        mockMvc.perform(put(BASE_PATH + "/" + NON_EXISTING_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateItem_shouldReturn400_whenRequestBodyIsInvalid() throws Exception {
        String requestBody = """
                {
                    "title": "",
                    "description": "Invalid update",
                    "assignedUserId": "11111111-1111-4111-a111-111111111111"
                }
                """;

        mockMvc.perform(put(BASE_PATH + "/" + EXISTING_ITEM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    // ==================== DELETE ====================

    @Test
    void deleteItem_shouldDeleteItem() throws Exception {
        String deleteItemId = "33333333-3333-3333-3333-333333333333";

        mockMvc.perform(delete(BASE_PATH + "/" + deleteItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify item is deleted
        mockMvc.perform(get(BASE_PATH + "/" + deleteItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteItem_shouldReturn404_whenItemDoesNotExist() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/" + NON_EXISTING_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
