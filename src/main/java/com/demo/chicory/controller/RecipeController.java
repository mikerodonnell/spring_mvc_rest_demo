package com.demo.chicory.controller;

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

import com.demo.chicory.exception.RecipeCreationException;
import com.demo.chicory.exception.RecipeLookupException;
import com.demo.chicory.model.Recipe;
import com.demo.chicory.serialization.RecipeRepresentation;
import com.demo.chicory.service.RecipeService;

@Controller
@RequestMapping("/recipe/")
public class RecipeController {

	private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	
	@RequestMapping(value="/{recipeUuid}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> getRecipe(@PathVariable("recipeUuid") String recipeUuid) {
		ResponseEntity responseEntity = null;
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
	
	
	// TODO: support stuff besides json
	/*
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public @ResponseBody Recipe createRecipe( @RequestBody RecipeRepresentation recipeRepresentation ) {
		Recipe recipe = RecipeService.createRecipe(recipeRepresentation);
		
		return recipe;
	}
	*/
	
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Object> createRecipe( @RequestBody RecipeRepresentation recipeRepresentation ) {
		ResponseEntity responseEntity = null;
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
	
	
	
}
