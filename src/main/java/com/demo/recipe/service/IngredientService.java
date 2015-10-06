package com.demo.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.recipe.dao.IngredientDao;
import com.demo.recipe.model.Ingredient;

@Service
public class IngredientService {

	@Autowired
	private IngredientDao ingredientDao;
	
	/**
	 * Determine if the given Ingredient already exists in the datastore or not.
	 * 
	 * @param ingredient
	 * @return true if the given Ingredient exists in the datastore; false otherwise, including null.
	 */
	public boolean ingredientExists( final Ingredient ingredient ) {
		if(ingredient==null)
			return false;
		
		return ( ingredientDao.getIngredient(ingredient.getName()) != null);
	}

	/**
	 * Create a new Ingredient based on the given name.
	 * 
	 * @param name
	 * @return the newly persisted Ingredient.
	 */
	public Ingredient createIngredient( final String name ) {
		return ingredientDao.saveIngredient( new Ingredient(name) );
	}

}
