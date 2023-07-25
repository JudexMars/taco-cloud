package judexdev.tacocloud.controllers.converters;

import judexdev.tacocloud.domain.Ingredient;

import judexdev.tacocloud.repository.IngredientRepository;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    IngredientRepository ingredientsRepo;

    public IngredientByIdConverter(IngredientRepository ingredientsRepo) {
        this.ingredientsRepo = ingredientsRepo;
    }

    @Override
    public Ingredient convert(@NonNull String id) {
        return ingredientsRepo.findById(id).orElse(null);
    }
}
