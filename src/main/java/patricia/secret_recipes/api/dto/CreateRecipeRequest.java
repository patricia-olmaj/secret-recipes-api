package patricia.secret_recipes.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import patricia.secret_recipes.recipes.persistence.Category;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequest {

    public String name;

    public Category category;

    public String ingredients;

    public String instructions;
}
