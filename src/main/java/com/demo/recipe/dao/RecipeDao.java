package com.demo.recipe.dao;

import java.util.Set;

import com.demo.recipe.model.Recipe;

public interface RecipeDao {

	public Set<Recipe> getRecipes();

	public Recipe getRecipeByUuid(String uuid);

	public Recipe getRecipeByName(String recipeName);

	public Recipe saveRecipe(Recipe newRecipe);
	
	public Set<Recipe> searchRecipesByIngredientName(String ingredientName);

}
