package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j

@CrossOrigin(origins = {"http://localhost:3000"})
public class RecipeController {

    private final RecipeService service;

    private final RecipeEntityMapper mapper;

    public RecipeController(RecipeService service, RecipeEntityMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/api/recipes")
    public ResponseEntity<List<Recipe>> getRecipes() {
        return new ResponseEntity<>(service.getRecipes(), HttpStatus.OK);
    }

    @GetMapping("/api/recipes/recipe/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable UUID recipeId) {
        return new ResponseEntity<>(service.getRecipeById(recipeId), HttpStatus.OK);
    }

    @PostMapping(value = "/api/recipes")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(service.addRecipe(recipe), HttpStatus.OK);
    }

}
