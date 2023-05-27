package judexdev.tacocloud.domain;

import org.springframework.data.relational.core.mapping.Table;

@Table
public record IngredientRef(String ingredient) {
}
