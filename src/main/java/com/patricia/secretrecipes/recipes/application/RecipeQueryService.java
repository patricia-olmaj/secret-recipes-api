package com.patricia.secretrecipes.recipes.application;

import com.patricia.secretrecipes.auth.persistence.UserEntity;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.exception.RecipeNotFoundException;
import com.patricia.secretrecipes.recipes.persistence.RecipeEntity;
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
        UserEntity user = (UserEntity) currentUser;
        RecipeEntity recipe = recipeRepository.findByIdAndUserId(recipeId,user.getId())
                .orElseThrow(() -> new RecipeNotFoundException("Receta no encontrada"));
        return recipeMapper.toResponse(recipe);
    }

    public List<RecipeResponse> listAllRecipes (UserDetails currentUser){
        UserEntity user = (UserEntity) currentUser;
        List<RecipeEntity> recipe = recipeRepository.findAllByUserId(user.getId());
        return recipeMapper.toResponseList(recipe);
    }
}
