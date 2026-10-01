package com.patricia.secretrecipes.recipes.application;

import com.patricia.secretrecipes.auth.persistence.UserEntity;
import com.patricia.secretrecipes.recipes.api.dto.CreateRecipeRequest;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.api.dto.UpdateRecipeRequest;
import com.patricia.secretrecipes.recipes.exception.RecipeNotFoundException;
import com.patricia.secretrecipes.recipes.persistence.RecipeEntity;
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
        UserEntity user = (UserEntity) currentUser;
        RecipeEntity recipe = recipeMapper.toEntity(request);
        recipe.setUser(user);
        RecipeEntity savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toResponse(savedRecipe);
    }

    public RecipeResponse updateRecipe (Integer recipeId, UpdateRecipeRequest request, UserDetails currentUser){
        UserEntity user = (UserEntity) currentUser;
        RecipeEntity recipeEntity = recipeRepository.findByIdAndUserId(recipeId,user.getId())
                .orElseThrow(() -> new RecipeNotFoundException("Receta no encontrada"));
        recipeMapper.update(request,recipeEntity);
        RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);
        return recipeMapper.toResponse(savedRecipe);
    }
}
