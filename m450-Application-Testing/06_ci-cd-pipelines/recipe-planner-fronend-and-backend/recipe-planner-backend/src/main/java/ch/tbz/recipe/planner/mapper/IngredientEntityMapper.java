package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.domain.Ingredient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface IngredientEntityMapper {

    Ingredient entityToDomain(IngredientEntity ingredientEntity);

    IngredientEntity domainToEntity(Ingredient ingredient);

    List<Ingredient> entitiesToDomains(List<IngredientEntity> ingredientEntities);
    List<IngredientEntity> domainsToEntities(List<Ingredient> ingredients);
}
