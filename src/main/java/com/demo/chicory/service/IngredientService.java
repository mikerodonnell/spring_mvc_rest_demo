package com.demo.chicory.service;

import com.demo.chicory.model.Ingredient;
import com.demo.chicory.stub.dao.StubIngredientDao;

public class IngredientService {

	public static boolean ingredientExists( final Ingredient ingredient ) {
		return ( StubIngredientDao.getIngredient(ingredient.getName()) != null);
	}

	public static Ingredient createIngredient( final String name ) {
		return StubIngredientDao.saveIngredient( new Ingredient(name) );
	}

}
