package com.github.sxpersxnic.tbz.todo.items;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemResponseDTO {
    private UUID id;
    private String title;
    private String description;
}
