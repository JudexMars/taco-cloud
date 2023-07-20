package judexdev.tacocloud;

import judexdev.tacocloud.domain.Ingredient;
import judexdev.tacocloud.domain.Taco;
import judexdev.tacocloud.repository.IngredientRepository;
import judexdev.tacocloud.repository.TacoRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

//     it's more convenient to use ApplicationRunner for loading default data into the db
//     because in this case we don't have to write SQL-queries ourselves
//     and our application becomes more flexible (we can easily replace the db even with a
//     non-relational one, like MongoDB)
    @Bean
    public ApplicationRunner dataLoader(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        return args -> {
            ingredientRepo.deleteAll(); // clear the ingredients table, then add all default ingredients
            var flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            var cornTortilla = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            var groundBeef = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            var carnitas = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
            var dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            var lettuce = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
            var cheddar = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
            var monterreyJack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            var salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            var sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);

            ingredientRepo.save(flourTortilla);
            ingredientRepo.save(cornTortilla);
            ingredientRepo.save(groundBeef);
            ingredientRepo.save(carnitas);
            ingredientRepo.save(dicedTomatoes);
            ingredientRepo.save(lettuce);
            ingredientRepo.save(cheddar);
            ingredientRepo.save(monterreyJack);
            ingredientRepo.save(salsa);
            ingredientRepo.save(sourCream);

            // create several tacos for testing purposes
            Taco taco1 = new Taco();
            taco1.setName("El Basado");
            taco1.setIngredients(List.of(flourTortilla, groundBeef, lettuce, cheddar, salsa, sourCream));

            Taco taco2 = new Taco();
            taco2.setName("Great Wall");
            taco2.setIngredients(List.of(cornTortilla, dicedTomatoes, monterreyJack));

            Taco taco3 = new Taco();
            taco3.setName("DEA's Nightmare");
            taco3.setIngredients(List.of(carnitas, dicedTomatoes, monterreyJack, salsa, sourCream));

            tacoRepo.save(taco1);
            tacoRepo.save(taco2);
            tacoRepo.save(taco3);
        };
    }
}
