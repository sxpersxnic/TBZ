package ch.tbz.recipe.planner.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {

    private UUID id;
    private String name;
    private String comment;
    private Unit unit;
    private int amount;

}
