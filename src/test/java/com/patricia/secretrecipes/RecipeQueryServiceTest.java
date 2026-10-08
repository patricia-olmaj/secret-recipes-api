package com.patricia.secretrecipes;


import com.patricia.secretrecipes.auth.persistence.Role;
import com.patricia.secretrecipes.auth.persistence.UserEntity;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.application.RecipeQueryService;
import com.patricia.secretrecipes.recipes.exception.RecipeNotFoundException;
import com.patricia.secretrecipes.recipes.mapper.RecipeMapper;
import com.patricia.secretrecipes.recipes.persistence.RecipeEntity;
import com.patricia.secretrecipes.recipes.persistence.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeQueryServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @InjectMocks
    private RecipeQueryService recipeQueryService;

    @Test
    void shouldReturnRecipeWhenFoundAndBelongsToUser() {
        Integer recipeId = 1;
        UserDetails currentUser = createDefaultUser("patricia123");
        RecipeEntity mockRecipe = new RecipeEntity();
        mockRecipe.setId(recipeId);
        mockRecipe.setName("Tortitas de avena");

        RecipeResponse mockResponse = new RecipeResponse();
        mockResponse.setId(recipeId);
        mockResponse.setName("Tortitas de avena");

        when(recipeRepository.findByIdAndUserId(recipeId, 1)).thenReturn(Optional.of(mockRecipe));

        when(recipeMapper.toResponse(mockRecipe)).thenReturn(mockResponse);

        RecipeResponse response = recipeQueryService.findRecipeById(recipeId, currentUser);

        assertNotNull(response);
        assertEquals("Tortitas de avena", response.getName());

        verify(recipeRepository, times(1)).findByIdAndUserId(recipeId, 1);
        verify(recipeMapper, times(1)).toResponse(mockRecipe);
    }

    @Test
    void shouldReturnAllRecipesForUser() {
        UserEntity currentUser = createDefaultUser("pepe123");

        List<RecipeEntity> mockRecipeEntities = List.of(new RecipeEntity(), new RecipeEntity());
        List<RecipeResponse> mockResponses = List.of(new RecipeResponse(), new RecipeResponse());

        when(recipeRepository.findAllByUserId(1)).thenReturn(mockRecipeEntities);
        when(recipeMapper.toResponseList(mockRecipeEntities)).thenReturn(mockResponses);
        List<RecipeResponse> result = recipeQueryService.listAllRecipes(currentUser);

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(recipeRepository, times(1)).findAllByUserId(1);
        verify(recipeMapper, times(1)).toResponseList(mockRecipeEntities);
    }

    @Test
    void shouldThrowExceptionWhenRecipeNotFoundOrNotOwnedByUser() {
        Integer recipeId = 1;
        UserEntity currentUser = createDefaultUser("paquito23");


        when(recipeRepository.findByIdAndUserId(recipeId, currentUser.getId()))
                .thenReturn(Optional.empty());

        RecipeNotFoundException exception = assertThrows(
                RecipeNotFoundException.class,
                () -> recipeQueryService.findRecipeById(recipeId, currentUser)
        );

        assertEquals("Receta no encontrada", exception.getMessage());

        verify(recipeRepository, times(1)).findByIdAndUserId(recipeId, currentUser.getId());

        verifyNoInteractions(recipeMapper);
    }


    public static UserEntity createDefaultUser(String username) {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUsername(username);
        user.setFirstname("Patricia");
        user.setLastname(" Olmedo");
        user.setPassword("$2a$10$passwordhashedskda");
        user.setCountry("España");
        user.setRole(Role.USER);
        return user;
    }


}
