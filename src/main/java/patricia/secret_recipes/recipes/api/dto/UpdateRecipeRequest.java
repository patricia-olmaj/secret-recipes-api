package patricia.secret_recipes.recipes.api.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import patricia.secret_recipes.recipes.persistence.Category;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeRequest {

    public String name;

    public Category category;

    public String ingredients;

    public String instructions;
}
