package com.patricia.secretrecipes.recipes.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.patricia.secretrecipes.recipes.persistence.Category;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    private Integer id;

    private String name;

    private Category category;

    private String ingredients;

    private String instructions;
}
