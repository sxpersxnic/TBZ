package ch.tbz.recipe.planner.unit.mapper;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;

@ExtendWith(SoftAssertionsExtension.class)
public class RecipeEntityMapperTest {

    private final RecipeEntityMapper mapper = Mappers.getMapper(RecipeEntityMapper.class);

    @Test
    void entityToDomain_shouldMapAllFields(SoftAssertions softly) {
        // Arrange
        UUID recipeId = UUID.randomUUID();
        UUID ingredientId = UUID.randomUUID();

        IngredientEntity ingredientEntity = new IngredientEntity(
            ingredientId,
            "Flour",
            "All-purpose flour",
            Unit.GRAMM,
            500
        );

        RecipeEntity recipeEntity = new RecipeEntity(
            recipeId,
            "Pancakes",
            "Delicious fluffy pancakes",
            "https://example.com/pancakes.jpg",
            List.of(ingredientEntity)
        );

        // Act
        Recipe recipe = mapper.entityToDomain(recipeEntity);

        // Assert
        softly.assertThat(recipe).isNotNull();
        softly.assertThat(recipe.getId()).isEqualTo(recipeId);
        softly.assertThat(recipe.getName()).isEqualTo("Pancakes");
        softly.assertThat(recipe.getDescription()).isEqualTo("Delicious fluffy pancakes");
        softly.assertThat(recipe.getImageUrl()).isEqualTo("https://example.com/pancakes.jpg");
        softly.assertThat(recipe.getIngredients()).hasSize(1);
        softly.assertThat(recipe.getIngredients().get(0).getId()).isEqualTo(ingredientId);
        softly.assertThat(recipe.getIngredients().get(0).getName()).isEqualTo("Flour");
    }

    @Test
    void domainToEntity_shouldMapAllFields(SoftAssertions softly) {
        // Arrange
        UUID recipeId = UUID.randomUUID();
        UUID ingredientId = UUID.randomUUID();

        Ingredient ingredient = new Ingredient(
            ingredientId,
            "Sugar",
            "White sugar",
            Unit.GRAMM,
            100
        );

        Recipe recipe = new Recipe(
            recipeId,
            "Cookies",
            "Chocolate chip cookies",
            "https://example.com/cookies.jpg",
            List.of(ingredient)
        );

        // Act
        RecipeEntity recipeEntity = mapper.domainToEntity(recipe);

        // Assert
        softly.assertThat(recipeEntity).isNotNull();
        softly.assertThat(recipeEntity.getId()).isEqualTo(recipeId);
        softly.assertThat(recipeEntity.getName()).isEqualTo("Cookies");
        softly.assertThat(recipeEntity.getDescription()).isEqualTo("Chocolate chip cookies");
        softly.assertThat(recipeEntity.getImageUrl()).isEqualTo("https://example.com/cookies.jpg");
        softly.assertThat(recipeEntity.getIngredients()).hasSize(1);
        softly.assertThat(recipeEntity.getIngredients().get(0).getId()).isEqualTo(ingredientId);
        softly.assertThat(recipeEntity.getIngredients().get(0).getName()).isEqualTo("Sugar");
    }

    @Test
    void entityToDomain_withNullEntity_shouldReturnNull(SoftAssertions softly) {
        // Act
        Recipe recipe = mapper.entityToDomain(null);

        // Assert
        softly.assertThat(recipe).isNull();
    }

    @Test
    void domainToEntity_withNullDomain_shouldReturnNull(SoftAssertions softly) {
        // Act
        RecipeEntity recipeEntity = mapper.domainToEntity(null);

        // Assert
        softly.assertThat(recipeEntity).isNull();
    }

    @Test
    void entityToDomain_withEmptyIngredients_shouldMapCorrectly(SoftAssertions softly) {
        // Arrange
        UUID recipeId = UUID.randomUUID();

        RecipeEntity recipeEntity = new RecipeEntity(
            recipeId,
            "Simple Recipe",
            "A recipe without ingredients",
            "https://example.com/simple.jpg",
            List.of()
        );

        // Act
        Recipe recipe = mapper.entityToDomain(recipeEntity);

        // Assert
        softly.assertThat(recipe).isNotNull();
        softly.assertThat(recipe.getId()).isEqualTo(recipeId);
        softly.assertThat(recipe.getName()).isEqualTo("Simple Recipe");
        softly.assertThat(recipe.getIngredients()).isEmpty();
    }

    @Test
    void domainToEntity_withMultipleIngredients_shouldMapAllIngredients(SoftAssertions softly) {
        // Arrange
        UUID recipeId = UUID.randomUUID();

        List<Ingredient> ingredients = List.of(
            new Ingredient(UUID.randomUUID(), "Eggs", "Large eggs", Unit.PIECE, 3),
            new Ingredient(UUID.randomUUID(), "Milk", "Whole milk", Unit.LITRE, 1),
            new Ingredient(UUID.randomUUID(), "Butter", "Unsalted", Unit.GRAMM, 50)
        );

        Recipe recipe = new Recipe(
            recipeId,
            "Breakfast",
            "Hearty breakfast",
            "https://example.com/breakfast.jpg",
            ingredients
        );

        // Act
        RecipeEntity recipeEntity = mapper.domainToEntity(recipe);

        // Assert
        softly.assertThat(recipeEntity).isNotNull();
        softly.assertThat(recipeEntity.getIngredients()).hasSize(3);
        softly.assertThat(recipeEntity.getIngredients())
            .extracting(IngredientEntity::getName)
            .containsExactly("Eggs", "Milk", "Butter");
    }
}
