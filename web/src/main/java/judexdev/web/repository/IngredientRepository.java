package judexdev.web.repository;

import judexdev.web.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}
