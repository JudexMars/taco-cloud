package judexdev.tacocloud.domain;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {
    private @NonNull String name;
    private @NonNull Ingredient.Type type;
}
