package judexdev.tacocloud.controllers.converters;

import java.util.Optional;

import judexdev.tacocloud.domain.Ingredient;
import judexdev.tacocloud.domain.IngredientUDT;
import judexdev.tacocloud.repository.IngredientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(@NotNull String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            return null;
        }
        return ingredient.map(i -> new IngredientUDT(i.getName(), i.getType())).get();
    }
}