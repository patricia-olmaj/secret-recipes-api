package com.patricia.secretrecipes.recipes.mapper;

import org.mapstruct.*;
import com.patricia.secretrecipes.recipes.api.dto.CreateRecipeRequest;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.api.dto.UpdateRecipeRequest;
import com.patricia.secretrecipes.recipes.persistence.RecipeEntity;

import java.util.List;

@Mapper (componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    RecipeEntity toEntity (CreateRecipeRequest request);

    RecipeResponse toResponse (RecipeEntity request);

    List<RecipeResponse> toResponseList(List<RecipeEntity> recipeEntityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateRecipeRequest request, @MappingTarget RecipeEntity target);

}
