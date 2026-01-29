package com.github.sxpersxnic.tbz.todo.items;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ItemRequestDTO {
    @NotBlank(message = "Title must not be blank")
    @Size(max = 50, message = "Title must be equal or less than 50 characters")
    private String title;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 200, message = "Description must be equal or less than 200 characters")
    private String description;

    @NotNull(message = "Priority must not be null")
    private Priority priority;

    private LocalDateTime dueDate;
    private boolean isCompleted;
    private Set<String> tags = new HashSet<>();
    private String assignedUserId;
}
