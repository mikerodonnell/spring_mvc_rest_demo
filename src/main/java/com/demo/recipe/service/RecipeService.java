package com.demo.recipe.service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.recipe.dao.RecipeDao;
import com.demo.recipe.exception.RecipeCreationException;
import com.demo.recipe.exception.RecipeLookupException;
import com.demo.recipe.model.Amount;
import com.demo.recipe.model.Ingredient;
import com.demo.recipe.model.Recipe;
import com.demo.recipe.serialization.RecipeRepresentation;

@Service
public class RecipeService {

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RecipeDao recipeDao;
	
	private static final Logger logger = Logger.getLogger(RecipeService.class);
	
	/**
	 * Create a new Recipe based on a given incomplete Recipe representation from the Controller. This is the top level for handling things like assigning unique identifiers,
	 * checking what ingredients already exist, lookup/save with database, and any other work we don't want the controller involved in.
	 * 
	 * @param recipeRepresentation
	 * @return
	 */
	public Recipe createRecipe( final RecipeRepresentation recipeRepresentation ) {
		
		final String newRecipeName = recipeRepresentation.getRecipeName();
		
		if( newRecipeName==null || newRecipeName.isEmpty() )
			throw new RecipeCreationException("Recipe name is required!");
		
		if( recipeNameExists(newRecipeName) )
			throw new RecipeCreationException("Recipe name \"" + newRecipeName + "\" already exists. Recipe names must be unique!");
		
		final Recipe recipe = new Recipe(UUID.randomUUID().toString(), recipeRepresentation.getRecipeName());
		
		extractOptionalRecipeData(recipe, recipeRepresentation);
		extractIngredients(recipe, recipeRepresentation);
		
		logger.info("saving new recipe now: " + recipe);
		return recipeDao.saveRecipe(recipe); // TODO: cascade ingredients relationships ... not ingredients themselves...
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

	
	private void extractIngredients( final Recipe recipe, final RecipeRepresentation recipeRepresentation ) {
		extractIngredients( recipe, recipeRepresentation, false );
	}
	
	/**
	 * Add the ingredients from the given Recipe representation (originating from the controller) to the to-be-persistent instance. This is done here rather than the
	 * Recipe model because we support dynamically saving ingredients for future recipes, which requires DAO invocation at this step.
	 * 
	 * @param recipe
	 * @param recipeRepresentation
	 */
	private void extractIngredients( final Recipe recipe, final RecipeRepresentation recipeRepresentation, boolean isUpdate ) {
		
		Map<Ingredient, Amount> newIngredients = recipeRepresentation.getIngredients();
		
		if( !isUpdate && newIngredients.isEmpty() ) // ensure that updates aren't affected here...an update doesn't have to add ingredients
			throw new RecipeCreationException("Can't create a recipe without ingredients!");
		
		for( Ingredient ingredient : newIngredients.keySet() ) {
			
			if( !ingredientService.ingredientExists(ingredient) ) {
				// this is a new ingredient to us, save it for future recipes
				ingredient = ingredientService.createIngredient( ingredient.getName() ); // this triggers the persist
			}
			
			// now add the ingredident regardless. this overwrites in Update cases, that's the desired behavior.
			recipe.addIngredient( ingredient, newIngredients.get(ingredient) );
		}
		
	}

	public Recipe getRecipe( final String recipeUuid ) {
		Recipe recipe = recipeDao.getRecipeByUuid(recipeUuid);
		if( recipe == null)
			throw new RecipeLookupException("recipe \"" + recipeUuid + "\" does not exist!");
		
		return recipe;
	}
	
	private boolean recipeNameExists( final String newRecipeName ) {
		return ( recipeDao.getRecipeByName(newRecipeName) != null);
	}

	public Set<Recipe> searchRecipesByIngredientName( final String ingredientName ) {
		return recipeDao.searchRecipesByIngredientName(ingredientName) ;
	}
	
	
	/**
	 * Update the recipe matching the given UUID with any non-null values found in the given recipeRepresentation.
	 * 
	 * @param recipeUuid
	 * @param recipeRepresentation
	 * @return
	 */
	public Recipe updateRecipe( final String recipeUuid, final RecipeRepresentation recipeRepresentation ) {
		// no support for wiping out existing ingredients ... just additive.
		final Recipe recipe = getRecipe( recipeUuid ); // RecipeLookupException thrown here if not exists
		
		extractOptionalRecipeData(recipe, recipeRepresentation);
		extractIngredients(recipe, recipeRepresentation, true);
		
		logger.info("saving updates to recipe now: " + recipe);
		return recipe;
	}
}
