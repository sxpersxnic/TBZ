package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class)
public interface RecipeEntityMapper {

    Recipe entityToDomain(RecipeEntity recipeEntity);

    RecipeEntity domainToEntity(Recipe recipe);


}
