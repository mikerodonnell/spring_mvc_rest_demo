package com.demo.recipe.dao;

import com.demo.recipe.model.Recipe;

import java.util.Set;

public interface RecipeDao {
    Recipe getRecipeByUuid(String uuid);

    Recipe getRecipeByName(String recipeName);

    Recipe saveRecipe(Recipe newRecipe);

    Set<Recipe> searchRecipesByIngredientName(String ingredientName);
}
