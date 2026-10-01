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

    public Integer id;

    public String name;

    public Category category;

    public String ingredients;

    public String instructions;
}
