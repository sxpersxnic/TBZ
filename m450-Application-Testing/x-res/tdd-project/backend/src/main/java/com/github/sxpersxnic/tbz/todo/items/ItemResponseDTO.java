package com.github.sxpersxnic.tbz.todo.items;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class ItemResponseDTO {
    private UUID id;
    private UUID assignedUserId;
    private String title;
    private String description;
    private boolean isCompleted;
    private LocalDateTime dueDate;
    private Priority priority;
    private Set<String> tags = new HashSet<>();
}
