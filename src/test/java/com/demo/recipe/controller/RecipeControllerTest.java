package com.demo.recipe.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.recipe.model.Recipe;

public class RecipeControllerTest {

	private static final String EXTANT_RECIPE_UUID = "c5515b57-55e9-4f37-963b-dc73ec399fb8";
	private static final String NONEXISTANT_RECIPE_UUID = "fd2a892f-f088-414a-a7fa-81e7f7ee8880";
	
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
