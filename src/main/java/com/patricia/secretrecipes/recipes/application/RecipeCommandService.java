package com.patricia.secretrecipes.recipes.application;

import com.patricia.secretrecipes.recipes.api.dto.CreateRecipeRequest;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.api.dto.UpdateRecipeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.patricia.secretrecipes.recipes.mapper.RecipeMapper;
import com.patricia.secretrecipes.recipes.persistence.RecipeRepository;

@Service
@RequiredArgsConstructor
public class RecipeCommandService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeResponse createRecipe (CreateRecipeRequest request, UserDetails currentUser){
        return new RecipeResponse();
    }

    public RecipeResponse updateRecipe (UpdateRecipeRequest request, UserDetails currentUser){
        return new RecipeResponse();
    }
}
