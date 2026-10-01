package com.patricia.secretrecipes.recipes.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
