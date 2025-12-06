package ch.tbz.recipe.planner.unit.mapper;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.mapper.IngredientEntityMapper;

@ExtendWith(SoftAssertionsExtension.class)
public class IngredientEntityMapperTest {

    private final IngredientEntityMapper mapper = Mappers.getMapper(IngredientEntityMapper.class);

    @Test
    void entityToDomain_shouldMapAllFields(SoftAssertions softly) {
        // Arrange
        UUID id = UUID.randomUUID();
        IngredientEntity entity = new IngredientEntity(
            id,
            "Flour",
            "All-purpose flour",
            Unit.GRAMM,
            500
        );

        // Act
        Ingredient ingredient = mapper.entityToDomain(entity);

        // Assert
        softly.assertThat(ingredient).isNotNull();
        softly.assertThat(ingredient.getId()).isEqualTo(id);
        softly.assertThat(ingredient.getName()).isEqualTo("Flour");
        softly.assertThat(ingredient.getComment()).isEqualTo("All-purpose flour");
        softly.assertThat(ingredient.getUnit()).isEqualTo(Unit.GRAMM);
        softly.assertThat(ingredient.getAmount()).isEqualTo(500);
    }

    @Test
    void domainToEntity_shouldMapAllFields(SoftAssertions softly) {
        // Arrange
        UUID id = UUID.randomUUID();
        Ingredient ingredient = new Ingredient(
            id,
            "Sugar",
            "White sugar",
            Unit.KILOGRAMM,
            2
        );

        // Act
        IngredientEntity entity = mapper.domainToEntity(ingredient);

        // Assert
        softly.assertThat(entity).isNotNull();
        softly.assertThat(entity.getId()).isEqualTo(id);
        softly.assertThat(entity.getName()).isEqualTo("Sugar");
        softly.assertThat(entity.getComment()).isEqualTo("White sugar");
        softly.assertThat(entity.getUnit()).isEqualTo(Unit.KILOGRAMM);
        softly.assertThat(entity.getAmount()).isEqualTo(2);
    }

    @Test
    void entityToDomain_withNullEntity_shouldReturnNull(SoftAssertions softly) {
        // Act
        Ingredient ingredient = mapper.entityToDomain(null);

        // Assert
        softly.assertThat(ingredient).isNull();
    }

    @Test
    void domainToEntity_withNullDomain_shouldReturnNull(SoftAssertions softly) {
        // Act
        IngredientEntity entity = mapper.domainToEntity(null);

        // Assert
        softly.assertThat(entity).isNull();
    }

    @Test
    void entitiesToDomains_shouldMapAllEntities(SoftAssertions softly) {
        // Arrange
        List<IngredientEntity> entities = List.of(
            new IngredientEntity(UUID.randomUUID(), "Eggs", "Large eggs", Unit.PIECE, 3),
            new IngredientEntity(UUID.randomUUID(), "Milk", "Whole milk", Unit.LITRE, 1),
            new IngredientEntity(UUID.randomUUID(), "Butter", "Unsalted", Unit.GRAMM, 100)
        );

        // Act
        List<Ingredient> ingredients = mapper.entitiesToDomains(entities);

        // Assert
        softly.assertThat(ingredients).hasSize(3);
        softly.assertThat(ingredients).extracting(Ingredient::getName)
            .containsExactly("Eggs", "Milk", "Butter");
        softly.assertThat(ingredients).extracting(Ingredient::getUnit)
            .containsExactly(Unit.PIECE, Unit.LITRE, Unit.GRAMM);
        softly.assertThat(ingredients).extracting(Ingredient::getAmount)
            .containsExactly(3, 1, 100);
    }

    @Test
    void domainsToEntities_shouldMapAllDomains(SoftAssertions softly) {
        // Arrange
        List<Ingredient> ingredients = List.of(
            new Ingredient(UUID.randomUUID(), "Salt", "Table salt", Unit.GRAMM, 10),
            new Ingredient(UUID.randomUUID(), "Pepper", "Black pepper", Unit.GRAMM, 5)
        );

        // Act
        List<IngredientEntity> entities = mapper.domainsToEntities(ingredients);

        // Assert
        softly.assertThat(entities).hasSize(2);
        softly.assertThat(entities).extracting(IngredientEntity::getName)
            .containsExactly("Salt", "Pepper");
        softly.assertThat(entities).extracting(IngredientEntity::getUnit)
            .containsExactly(Unit.GRAMM, Unit.GRAMM);
    }

    @Test
    void entitiesToDomains_withEmptyList_shouldReturnEmptyList(SoftAssertions softly) {
        // Act
        List<Ingredient> ingredients = mapper.entitiesToDomains(List.of());

        // Assert
        softly.assertThat(ingredients).isEmpty();
    }

    @Test
    void domainsToEntities_withEmptyList_shouldReturnEmptyList(SoftAssertions softly) {
        // Act
        List<IngredientEntity> entities = mapper.domainsToEntities(List.of());

        // Assert
        softly.assertThat(entities).isEmpty();
    }

    @Test
    void entityToDomain_withAllUnitTypes_shouldMapCorrectly(SoftAssertions softly) {
        // Test all Unit enum values
        for (Unit unit : Unit.values()) {
            IngredientEntity entity = new IngredientEntity(
                UUID.randomUUID(),
                "Test",
                "Test comment",
                unit,
                1
            );

            Ingredient ingredient = mapper.entityToDomain(entity);

            softly.assertThat(ingredient.getUnit())
                .as("Unit %s should be mapped correctly", unit)
                .isEqualTo(unit);
        }
    }
}
