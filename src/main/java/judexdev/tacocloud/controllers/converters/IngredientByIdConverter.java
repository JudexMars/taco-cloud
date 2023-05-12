package judexdev.tacocloud.controllers.converters;

import judexdev.tacocloud.domain.Ingredient;

import judexdev.tacocloud.repository.IngredientRepository;
import judexdev.tacocloud.repository.JdbcIngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

//    private final Map<String, Ingredient> ingredientMap = new HashMap<>();

    IngredientRepository ingredientsRepo;

    public IngredientByIdConverter(JdbcIngredientRepository ingredientsRepo) {
        this.ingredientsRepo = ingredientsRepo;
    }

//    public IngredientByIdConverter() {
//        ingredientMap.put("FLTO",
//                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
//        ingredientMap.put("COTO",
//                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
//        ingredientMap.put("GRBF",
//                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
//        ingredientMap.put("CARN",
//                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
//        ingredientMap.put("TMTO",
//                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
//        ingredientMap.put("LETC",
//                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
//        ingredientMap.put("CHED",
//                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
//        ingredientMap.put("JACK",
//                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
//        ingredientMap.put("SLSA",
//                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
//        ingredientMap.put("SRCR",
//                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
//
//    }

    @Override
    public Ingredient convert(String id) {
//        return ingredientMap.get(id);
        return ingredientsRepo.findById(id).orElse(null);
    }
}
