package ch.tbz.recipe.planner.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {

    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private List<Ingredient> ingredients;
}
