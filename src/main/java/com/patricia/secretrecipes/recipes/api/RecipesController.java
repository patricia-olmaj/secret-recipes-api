package com.patricia.secretrecipes.recipes.api;

import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Controller
@RequestMapping("/api/v1/recipes")
public class RecipesController {
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable Integer id, @AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(new RecipeResponse());
    }
    @GetMapping("/my-recipes")
    public ResponseEntity<List<RecipeResponse>> getAllMyRecipes(@AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/")
}
