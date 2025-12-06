package ch.tbz.recipe.planner;

import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class RecipePlannerApplication {

	private final static String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	private final static List<String> IMAGES =
			List.of("https://img.freepik.com/free-photo/grilled-gourmet-burger-with-cheese-tomato-onion-french-fries-generated-by-artificial-intelligence_25030-63181.jpg",
					"https://img.taste.com.au/5qlr1PkR/taste/2016/11/spaghetti-bolognese-106560-1.jpeg",
					"https://www.kitchensanctuary.com/wp-content/uploads/2020/04/Chicken-Fried-Rice-square-FS-.jpg",
					"https://www.deliciousmagazine.co.uk/wp-content/uploads/2020/01/lasagne.jpg",
					"https://recipeimages.migros.ch/crop/v-w-1200-h-630-a-center_center/ac38873942ada8c8154f0da0a748adf486a304d8/pommes-frites-0-16-9.jpg"
					);
	public static void main(String[] args) {
		SpringApplication.run(RecipePlannerApplication.class, args);
	}

	@Autowired
	RecipeEntityMapper mapper;

	@Bean
	CommandLineRunner init(RecipeRepository recipeRepository) {
		Random rand = new Random();

		return args -> {
			Stream.of(
					"Lasagne al Forno", "Spaghetti Bolognese", "Fried Rice", "Spaghetti Bolognese", "Pommes Frites",
					"Lasagne al Forno", "Spaghetti Bolognese", "Fried Rice", "Spaghetti Bolognese", "Pommes Frites",
					"Lasagne al Forno", "Spaghetti Bolognese", "Fried Rice", "Spaghetti Bolognese", "Pommes Frites").forEach(name -> {
				int randomIndex = rand.nextInt(IMAGES.size());
				Recipe recipe = new Recipe(UUID.randomUUID(), name, DESCRIPTION, IMAGES.get(randomIndex), List.of(new Ingredient(UUID.randomUUID(), "Tomato", "The big ones", Unit.PIECE, 5)));
				var entity = mapper.domainToEntity(recipe);
				recipeRepository.save(entity);
			});
			recipeRepository.findAll().forEach(s -> log.info(s.toString()));
		};
	}

}
