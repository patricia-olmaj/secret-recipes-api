package patricia.secret_recipes.recipes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patricia.secret_recipes.api.dto.RecipeResponse;
import patricia.secret_recipes.recipes.mapper.RecipeMapper;
import patricia.secret_recipes.recipes.persistence.RecipeRepository;

@Service
@RequiredArgsConstructor
public class RecipeQueryService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

}
