package com.patricia.secretrecipes.recipes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
    List<RecipeEntity> findAllByUserId(Integer id);

    Optional<RecipeEntity> findByIdAndUserId(Integer recipeId, Integer userId);

    boolean existsByIdAndUserId(Integer recipeId,Integer userId);
}
