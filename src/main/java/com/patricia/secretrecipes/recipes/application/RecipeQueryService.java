package com.patricia.secretrecipes.recipes.application;

import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.patricia.secretrecipes.recipes.mapper.RecipeMapper;
import com.patricia.secretrecipes.recipes.persistence.RecipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeQueryService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeResponse findRecipeById (Integer recipeId, UserDetails currentUser){
        return new RecipeResponse();
    }

    public List<RecipeResponse> listAllRecipes (UserDetails currentUser){
        return  List.of();
    }
}
