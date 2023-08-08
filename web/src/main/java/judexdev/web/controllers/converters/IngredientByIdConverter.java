package judexdev.web.controllers.converters;

import judexdev.web.domain.Ingredient;

import judexdev.web.repository.IngredientRepository;
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
