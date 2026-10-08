package com.patricia.secretrecipes;

import com.patricia.secretrecipes.auth.persistence.Role;
import com.patricia.secretrecipes.auth.persistence.UserEntity;
import com.patricia.secretrecipes.recipes.api.dto.CreateRecipeRequest;
import com.patricia.secretrecipes.recipes.api.dto.RecipeResponse;
import com.patricia.secretrecipes.recipes.api.dto.UpdateRecipeRequest;
import com.patricia.secretrecipes.recipes.application.RecipeCommandService;
import com.patricia.secretrecipes.recipes.exception.RecipeNotFoundException;
import com.patricia.secretrecipes.recipes.mapper.RecipeMapper;
import com.patricia.secretrecipes.recipes.persistence.RecipeEntity;
import com.patricia.secretrecipes.recipes.persistence.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeCommandServiceTest {
    @Mock
    private RecipeMapper recipeMapper;
    @Mock
    private RecipeRepository recipeRepository;
    @InjectMocks
    private RecipeCommandService recipeCommandService;


    @Test
    void shouldCreateRecipeSuccessfully() {
        UserEntity currentUser = createDefaultUser("patricia");
        CreateRecipeRequest request = new CreateRecipeRequest();
        request.setName("Tortitas de avena");

        RecipeEntity mappedEntity = new RecipeEntity();
        RecipeEntity savedEntity = new RecipeEntity();
        savedEntity.setId(10);
        savedEntity.setName("Tortitas de avena");
        savedEntity.setUser(currentUser);

        RecipeResponse expectedResponse = new RecipeResponse();
        expectedResponse.setId(10);
        expectedResponse.setName("Tortitas de avena");

        when(recipeMapper.toEntity(request)).thenReturn(mappedEntity);
        when(recipeRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(recipeMapper.toResponse(savedEntity)).thenReturn(expectedResponse);

        RecipeResponse response = recipeCommandService.create(request, currentUser);

        assertNotNull(response);
        assertEquals("Tortitas de avena", response.getName());
        assertEquals(currentUser, mappedEntity.getUser());

        verify(recipeMapper, times(1)).toEntity(request);
        verify(recipeRepository, times(1)).save(mappedEntity);
        verify(recipeMapper, times(1)).toResponse(savedEntity);
    }

    @Test
    void shouldUpdateRecipeSuccessfully() {
        Integer recipeId = 1;
        UserEntity currentUser = createDefaultUser("patricia23");


        UpdateRecipeRequest request = new UpdateRecipeRequest();
        request.setName("Tortilla de patatas");
        request.setIngredients("5 patatas, 3 huevos, 1 cucharadita de sal");

        RecipeEntity existingEntity = new RecipeEntity();
        existingEntity.setId(recipeId);
        existingEntity.setName("Tortilla de patatas");
        existingEntity.setIngredients("5 patatas y 5 huevos");
        existingEntity.setUser(currentUser);

        RecipeEntity savedEntity = new RecipeEntity();
        savedEntity.setId(recipeId);
        savedEntity.setName("Tortilla de patatas");

        RecipeResponse expectedResponse = new RecipeResponse();
        expectedResponse.setId(recipeId);
        expectedResponse.setName("Tortilla de patatas");
        expectedResponse.setIngredients("5 patatas, 3 huevos, 1 cucharadita de sal");

        when(recipeRepository.findByIdAndUserId(recipeId, currentUser.getId()))
                .thenReturn(Optional.of(existingEntity));
        when(recipeRepository.save(existingEntity)).thenReturn(savedEntity);
        when(recipeMapper.toResponse(savedEntity)).thenReturn(expectedResponse);

        RecipeResponse response = recipeCommandService.update(recipeId, request, currentUser);

        assertNotNull(response);
        assertEquals("5 patatas, 3 huevos, 1 cucharadita de sal", response.getIngredients());

        verify(recipeRepository, times(1)).findByIdAndUserId(recipeId, currentUser.getId());
        verify(recipeMapper, times(1)).update(request, existingEntity);
        verify(recipeRepository, times(1)).save(existingEntity);
        verify(recipeMapper, times(1)).toResponse(savedEntity);
    }

    @Test
    void shouldDeleteRecipeSuccessfully() {
        Integer recipeId = 1;
        UserEntity currentUser = createDefaultUser("patricia");

        when(recipeRepository.existsByIdAndUserId(recipeId, currentUser.getId()))
                .thenReturn(true);

        recipeCommandService.delete(recipeId, currentUser);

        verify(recipeRepository, times(1)).existsByIdAndUserId(recipeId, currentUser.getId());
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingRecipe() {
        Integer recipeId = 1;
        UserEntity currentUser = createDefaultUser("patricia");

        UpdateRecipeRequest request = new UpdateRecipeRequest();
        request.setName("Tortilla de patatas");

        when(recipeRepository.findByIdAndUserId(recipeId, currentUser.getId()))
                .thenReturn(Optional.empty());

        RecipeNotFoundException exception = assertThrows(
                RecipeNotFoundException.class,
                () -> recipeCommandService.update(recipeId, request, currentUser)
        );

        assertEquals("Receta no encontrada", exception.getMessage());

        verify(recipeRepository, times(1)).findByIdAndUserId(recipeId, currentUser.getId());
        verify(recipeMapper, never()).update(any(), any());
        verify(recipeRepository, never()).save(any());
        verify(recipeMapper, never()).toResponse(any());
    }
    @Test
    void shouldNotDeleteRecipeWhenIsNotOwnedByUser() {
        Integer recipeId = 1;
        UserEntity otherUser = createDefaultUser("patricia34");
        otherUser.setId(2);

        when(recipeRepository.existsByIdAndUserId(recipeId, otherUser.getId()))
                .thenReturn(false);
        assertThrows(
                RecipeNotFoundException.class,
                () -> recipeCommandService.delete(recipeId, otherUser)
        );

        verify(recipeRepository, times(1)).existsByIdAndUserId(recipeId, 2);
        verify(recipeRepository, never()).deleteById(any());
    }

    public static UserEntity createDefaultUser(String username) {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUsername(username);
        user.setFirstname("Patricia");
        user.setLastname("Olmedo");
        user.setPassword("$2a$10$passwordhashedskda");
        user.setCountry("España");
        user.setRole(Role.USER);
        return user;
    }

}
