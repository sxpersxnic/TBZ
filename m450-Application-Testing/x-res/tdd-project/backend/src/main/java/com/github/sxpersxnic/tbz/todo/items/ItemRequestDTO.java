package com.github.sxpersxnic.tbz.todo.items;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDTO {
    @NotBlank(message = "Title must not be blank")
    @Size(max = 50, message = "Title must be equal or less than 50 characters")
    private String title;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 200, message = "Description must be equal or less than 200 characters")
    private String description;

    @NotBlank(message = "Completed must not be blank")
    private boolean isCompleted;
}
