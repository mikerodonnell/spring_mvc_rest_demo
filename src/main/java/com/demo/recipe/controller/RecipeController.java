package com.demo.recipe.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.recipe.exception.RecipeCreationException;
import com.demo.recipe.exception.RecipeLookupException;
import com.demo.recipe.model.Recipe;
import com.demo.recipe.serialization.RecipeRepresentation;
import com.demo.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe/")
public class RecipeController {

	private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	
	/**
	 * Retrieve a single Recipe by UUID. 
	 * 
	 * @param recipeUuid
	 * @return HTTP 200 and recipe details if recipe is found for the given UUID; HTTP 400 and error message otherwise.
	 */
	@RequestMapping(value="/{recipeUuid}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getRecipe( @PathVariable("recipeUuid") String recipeUuid ) {
		ResponseEntity<?> responseEntity = null;
		Recipe recipe = null;
		String errorMessage = null;
		try {
			recipe = RecipeService.getRecipe(recipeUuid);
		}
		catch( RecipeLookupException recipeLookupException ) {
			errorMessage = recipeLookupException.getMessage();
			// that's all we have to do here...recipe will remain null, so error HTTP response code will be used
		}
		if(recipe == null)
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST); // bad request.
		else
			responseEntity = new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	/**
	 * Get all recipes that contain the given ingredient.
	 * 
	 * @param ingredientName
	 * @return HTTP 200, and recipe(s) details if any are found.
	 */
	@RequestMapping(value="/search/{ingredientName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> searchRecipesByIngredientName( @PathVariable("ingredientName") String ingredientName ) {
		ResponseEntity<?> responseEntity = null;
		Set<Recipe> recipes = RecipeService.searchRecipesByIngredientName(ingredientName);
		
		responseEntity = new ResponseEntity< Set<Recipe> >(recipes, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	// TODO: support stuff besides json
	/**
	 * Create a new recipe from the given RecipeRepresentation.
	 * 
	 * @param recipeRepresentation
	 * @return HTTP 200 and recipe details if recipe is successfully created; HTTP 400 and error message otherwise (for example, a required value is not in the RecipeRepresentation).
	 */
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> createRecipe( @RequestBody RecipeRepresentation recipeRepresentation ) {
		ResponseEntity<?> responseEntity = null;
		Recipe recipe = null;
		String errorMessage = null;
		
		try {
			recipe = RecipeService.createRecipe(recipeRepresentation);
		}
		catch(RecipeCreationException recipeCreationException) {
			errorMessage = recipeCreationException.getMessage();
		}
		
		if(recipe == null)
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST); // bad request.
		else
			responseEntity = new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	// TODO: support stuff besides json
	/**
	 * Update an extant recipe based on the given RecipeRepresentation.
	 * 
	 * 
	 * @param recipeUuid
	 * @param recipeRepresentation
	 * @return HTTP 200 and recipe details if recipe is successfully updated; HTTP 400 and error message otherwise (for example, the recipe for the given UUID does not exist).
	 */
	@RequestMapping(value = "/update/{recipeUuid}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateRecipe( @PathVariable("recipeUuid") String recipeUuid, @RequestBody RecipeRepresentation recipeRepresentation ) {
		ResponseEntity<?> responseEntity = null;
		Recipe recipe = null;
		String errorMessage = null;
		
		try {
			recipe = RecipeService.updateRecipe( recipeUuid, recipeRepresentation );
		}
		catch(RecipeCreationException recipeCreationException) {
			errorMessage = recipeCreationException.getMessage();
		}
		
		if(recipe == null)
			responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST); // bad request.
		else
			responseEntity = new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
		
		return responseEntity;
	}
}
