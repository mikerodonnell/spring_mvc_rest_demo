package com.demo.chicory.service;

import java.util.Map;
import java.util.UUID;

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
		
		final String newRecipeName = recipeRepresentation.getRecipeName();
		
		if( newRecipeName==null || newRecipeName.isEmpty() )
			throw new RecipeCreationException("Recipe name is required!");
		
		if( recipeNameExists(newRecipeName) )
			throw new RecipeCreationException("Recipe name \"" + newRecipeName + "\" already exists. Recipe names must be unique!");
		
		final Recipe recipe = new Recipe(UUID.randomUUID().toString(), recipeRepresentation.getRecipeName());
		
		extractOptionalRecipeData(recipe, recipeRepresentation);
		extractIngredients(recipe, recipeRepresentation);
		
		return StubRecipeDao.saveRecipe(recipe); // TODO: cascade ingredients relationships ... not ingredients themselves...
	}

	/**
	 * helper method to load basic recipe data from the given RecipeRepresentation;
	 * 
	 * @param recipe
	 * @param recipeRepresentation
	 */
	private static void extractOptionalRecipeData( final Recipe recipe, final RecipeRepresentation recipeRepresentation ) {
		recipe.setInstructions( recipeRepresentation.getInstructions() );
		recipe.setPrepMinutes( recipeRepresentation.getPrepMinutes() );
		recipe.setCookMinutes( recipeRepresentation.getCookMinutes() );
	}

	
	private static void extractIngredients( final Recipe recipe, final RecipeRepresentation recipeRepresentation ) {
		extractIngredients( recipe, recipeRepresentation, false );
	}
	
	/**
	 * Add the ingredients from the given Recipe representation (originating from the controller) to the to-be-persistent instance. This is done here rather than the
	 * Recipe model because we support dynamically saving ingredients for future recipes, which requires DAO invocation at this step.
	 * 
	 * @param recipe
	 * @param recipeRepresentation
	 */
	private static void extractIngredients( final Recipe recipe, final RecipeRepresentation recipeRepresentation, boolean isUpdate ) {
		
		Map<Ingredient, Amount> newIngredients = recipeRepresentation.getIngredients();
		
		if( !isUpdate && newIngredients.isEmpty() ) // ensure that updates aren't affected here...an update doesn't have to add ingredients
			throw new RecipeCreationException("Can't create a recipe without ingredients!");
		
		for( Ingredient ingredient : newIngredients.keySet() ) {
			
			if( !IngredientService.ingredientExists(ingredient) ) {
				// this is a new ingredient to us, save it for future recipes
				ingredient = IngredientService.createIngredient( ingredient.getName() ); // this triggers the persist
			}
			
			// now add the ingredident regardless. this overwrites in Update cases, that's the desired behavior.
			recipe.addIngredient( ingredient, newIngredients.get(ingredient) );
		}
		
	}

	public static Recipe getRecipe( final String recipeUuid ) {
		Recipe recipe = StubRecipeDao.getRecipeByUuid(recipeUuid);
		if( recipe == null)
			throw new RecipeLookupException("recipe \"" + recipeUuid + "\" does not exist!");
		
		return recipe;
	}
	
	private static boolean recipeNameExists( final String newRecipeName ) {
		return ( StubRecipeDao.getRecipeByName(newRecipeName) != null);
	}

	
	/**
	 * Update the recipe matching the given UUID with any non-null values found in the given recipeRepresentation.
	 * 
	 * @param recipeUuid
	 * @param recipeRepresentation
	 * @return
	 */
	public static Recipe updateRecipe( final String recipeUuid, final RecipeRepresentation recipeRepresentation ) {
		// TODO: currently no support for wiping out existing ingredients ... just additive right now
		final Recipe recipe = getRecipe( recipeUuid ); // RecipeLookupException thrown here if not exists
		
		extractOptionalRecipeData(recipe, recipeRepresentation);
		extractIngredients(recipe, recipeRepresentation, true);
		
		return recipe;
	}
}
