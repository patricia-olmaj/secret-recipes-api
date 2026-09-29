package com.patricia.secretrecipes.recipes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.patricia.secretrecipes.recipes.mapper.RecipeMapper;
import com.patricia.secretrecipes.recipes.persistence.RecipeRepository;

@Service
@RequiredArgsConstructor
public class RecipeCommandService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;


}
