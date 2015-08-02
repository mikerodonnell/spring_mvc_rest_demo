package com.demo.chicory.service;

import java.util.Map;

import com.demo.chicory.exception.RecipeCreationException;
import com.demo.chicory.exception.RecipeLookupException;
import com.demo.chicory.model.Amount;
import com.demo.chicory.model.Ingredient;
import com.demo.chicory.model.Recipe;
import com.demo.chicory.serialization.RecipeRepresentation;
import com.demo.chicory.stub.dao.StubRecipeDao;

public class RecipeService {

	/**
	 * Create a new Recipe based on a given incomplete Recipe representation from the Controller. This is the top level for handling things like assigning unique identifiers,
	 * checking what ingredients already exist, lookup/save with database, and any other work we don't want the controller involved in.
	 * 
	 * @param recipeRepresentation
	 * @return
	 */
	public static Recipe createRecipe( final RecipeRepresentation recipeRepresentation ) {
		
		String newRecipeName = recipeRepresentation.getRecipeName();
		
		if( newRecipeName==null || newRecipeName.isEmpty() )
			throw new RecipeCreationException("Recipe name is required!");
		
		if( recipeNameExists(newRecipeName) )
			throw new RecipeCreationException("Recipe name \"" + newRecipeName + "\" already exists. Recipe names must be unique!");
		
		Recipe recipe = new Recipe(); 
		recipe.setRecipeName(recipeRepresentation.getRecipeName());
		
		addIngredients(recipe, recipeRepresentation);
		
		return StubRecipeDao.saveRecipe(recipe); // TODO: cascade ingredients relationships ... not ingredients themselves...
	}

	/**
	 * Add the ingredients from the given Recipe representation (originating from the controller) to the to-be-persistent instance. This is done here rather than the
	 * Recipe model because we support dynamically saving ingredients for future recipes, which requires DAO invocation at this step.
	 * 
	 * @param recipe
	 * @param recipeRepresentation
	 */
	private static void addIngredients( final Recipe recipe, final RecipeRepresentation recipeRepresentation) {
		
		Map<Ingredient, Amount> newIngredients = recipeRepresentation.getIngredients();
		
		if( newIngredients.isEmpty() )
			throw new RecipeCreationException("Can't create a recipe with no ingredients specified!");
		
		for( Ingredient ingredient : newIngredients.keySet() ) {
			if( !IngredientService.ingredientExists(ingredient) ) {
				// this is a new ingredient to us, save it for future recipes
				ingredient = IngredientService.createIngredient( ingredient.getName() ); // this triggers the persist
			}
			recipe.addIngredient( ingredient, newIngredients.get(ingredient) ); // add the ingredident regardless
		}
		
	}

	public static Recipe getRecipe(String recipeUuid) {
		Recipe recipe = StubRecipeDao.getRecipeByUuid(recipeUuid);
		if( recipe == null)
			throw new RecipeLookupException("recipe \"" + recipeUuid + "\" does not exist!");
		
		return recipe;
	}
	
	private static boolean recipeNameExists( final String newRecipeName ) {
		return ( StubRecipeDao.getRecipeByName(newRecipeName) != null);
	}
}
