package com.patricia.secretrecipes.recipes.api;

import com.patricia.secretrecipes.recipes.api.dto.CreateRecipeRequest;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.api.dto.UpdateRecipeRequest;
import com.patricia.secretrecipes.recipes.application.RecipeCommandService;
import com.patricia.secretrecipes.recipes.application.RecipeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@RestController
@Controller
@RequestMapping("/api/v1")
public class RecipesController {

    private final RecipeQueryService queryService;
    private final RecipeCommandService commandService;

    @GetMapping("/me/recipes/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Integer id, @AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(queryService.findRecipeById(id, currentUser));
    }
    @GetMapping("/me/recipes")
    public ResponseEntity<List<RecipeResponse>> getAllMyRecipes(@AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(queryService.listAllRecipes(currentUser));
    }

    @PostMapping("/recipes")
    public ResponseEntity<RecipeResponse> createRecipe (@RequestBody CreateRecipeRequest request, @AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(commandService.create(request,currentUser));
    }

    @PatchMapping("/recipes/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe (@PathVariable Integer id, @RequestBody UpdateRecipeRequest request, @AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(commandService.update(id,request,currentUser));
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id, @AuthenticationPrincipal UserDetails currentUser){
        commandService.delete(id,currentUser);
        return ResponseEntity.noContent().build();
    }
}
