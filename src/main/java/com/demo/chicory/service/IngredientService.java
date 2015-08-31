package com.demo.chicory.service;

import com.demo.chicory.model.Ingredient;
import com.demo.chicory.stub.dao.StubIngredientDao;

public class IngredientService {

	/**
	 * Determine if the given Ingredient already exists in the datastore or not.
	 * 
	 * @param ingredient
	 * @return true if the given Ingredient exists in the datastore; false otherwise, including null.
	 */
	public static boolean ingredientExists( final Ingredient ingredient ) {
		if(ingredient==null)
			return false;
		
		return ( StubIngredientDao.getIngredient(ingredient.getName()) != null);
	}

	/**
	 * Create a new Ingredient based on the given name.
	 * 
	 * @param name
	 * @return the newly persisted Ingredient.
	 */
	public static Ingredient createIngredient( final String name ) {
		return StubIngredientDao.saveIngredient( new Ingredient(name) );
	}

}
