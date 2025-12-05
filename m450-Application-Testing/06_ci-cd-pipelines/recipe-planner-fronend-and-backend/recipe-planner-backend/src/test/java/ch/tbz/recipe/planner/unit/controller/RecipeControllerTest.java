package ch.tbz.recipe.planner.unit.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/recipes";
    }

    @Test
    void getRecipes_shouldReturnListOfRecipes() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("$", is(notNullValue()));
    }

    @Test
    void addRecipe_shouldCreateAndReturnRecipe() {
        Ingredient ingredient = new Ingredient(
            null,
            "Flour",
            "All-purpose flour",
            Unit.GRAMM,
            500
        );

        Recipe recipe = new Recipe(
            null,
            "Pancakes",
            "Delicious fluffy pancakes",
            "https://example.com/pancakes.jpg",
            List.of(ingredient)
        );

        given()
            .contentType(ContentType.JSON)
            .body(recipe)
        .when()
            .post()
        .then()
            .statusCode(200)
            .body("id", is(notNullValue()))
            .body("name", equalTo("Pancakes"))
            .body("description", equalTo("Delicious fluffy pancakes"))
            .body("imageUrl", equalTo("https://example.com/pancakes.jpg"))
            .body("ingredients", hasSize(1))
            .body("ingredients[0].name", equalTo("Flour"))
            .body("ingredients[0].amount", equalTo(500))
            .body("ingredients[0].unit", equalTo("GRAMM"));
    }

    @Test
    void getRecipeById_shouldReturnRecipe() {
        // First, create a recipe
        Ingredient ingredient = new Ingredient(
            null,
            "Sugar",
            "White sugar",
            Unit.GRAMM,
            100
        );

        Recipe recipe = new Recipe(
            null,
            "Cookies",
            "Chocolate chip cookies",
            "https://example.com/cookies.jpg",
            List.of(ingredient)
        );

        String recipeId = given()
            .contentType(ContentType.JSON)
            .body(recipe)
        .when()
            .post()
        .then()
            .statusCode(200)
            .extract()
            .path("id");

        // Then, retrieve the recipe by ID
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/recipe/{recipeId}", recipeId)
        .then()
            .statusCode(200)
            .body("id", equalTo(recipeId))
            .body("name", equalTo("Cookies"))
            .body("description", equalTo("Chocolate chip cookies"))
            .body("imageUrl", equalTo("https://example.com/cookies.jpg"))
            .body("ingredients", hasSize(1))
            .body("ingredients[0].name", equalTo("Sugar"));
    }

    @Test
    void addRecipe_withMultipleIngredients_shouldCreateRecipeWithAllIngredients() {
        List<Ingredient> ingredients = List.of(
            new Ingredient(null, "Eggs", "Large eggs", Unit.PIECE, 3),
            new Ingredient(null, "Milk", "Whole milk", Unit.DECILITRE, 2),
            new Ingredient(null, "Butter", "Unsalted butter", Unit.GRAMM, 50)
        );

        Recipe recipe = new Recipe(
            null,
            "Omelette",
            "Classic French omelette",
            "https://example.com/omelette.jpg",
            ingredients
        );

        given()
            .contentType(ContentType.JSON)
            .body(recipe)
        .when()
            .post()
        .then()
            .statusCode(200)
            .body("id", is(notNullValue()))
            .body("name", equalTo("Omelette"))
            .body("ingredients", hasSize(3))
            .body("ingredients.name", hasItems("Eggs", "Milk", "Butter"));
    }
}
