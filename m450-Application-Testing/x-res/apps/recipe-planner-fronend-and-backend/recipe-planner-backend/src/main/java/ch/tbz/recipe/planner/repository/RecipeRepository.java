package ch.tbz.recipe.planner.repository;


import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> findById(UUID id);
    List<RecipeEntity> findAll();

}
