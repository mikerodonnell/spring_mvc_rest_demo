package com.demo.recipe.dao;

import java.util.Set;

import com.demo.recipe.model.Ingredient;

public interface IngredientDao {

	public Set<Ingredient> getIngredients();

	public Ingredient getIngredient(String name);

	public Ingredient saveIngredient(Ingredient newIngredient);

}
