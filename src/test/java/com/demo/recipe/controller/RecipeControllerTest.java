package com.demo.recipe.controller;

import static org.junit.Assert.assertEquals;
import static com.demo.recipe.RecipeTestData.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.recipe.model.Recipe;

public class RecipeControllerTest {
	
	@Test
	public void testGetRecipe() {
		RecipeController recipeController = new RecipeController();
		ResponseEntity<?> response = recipeController.getRecipe(EXTANT_RECIPE_UUID);
		
		assertEquals( HttpStatus.OK, response.getStatusCode() );
		
		Recipe returnedRecipe = (Recipe) response.getBody();
		assertEquals( EXTANT_RECIPE_UUID, returnedRecipe.getUuid() );
	}
	
	@Test
	public void testGetRecipeNonexistant() {
		RecipeController recipeController = new RecipeController();
		ResponseEntity<?> response = recipeController.getRecipe(NONEXISTANT_RECIPE_UUID);
		
		assertEquals( HttpStatus.BAD_REQUEST, response.getStatusCode() );
	}
}
