package com.demo.chicory.model;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

	private String uuid;
	private String recipeName;
	private Map<Ingredient, Amount> ingredients = new HashMap<Ingredient, Amount>();

	// TODO: take away default constructor once verifying that's ok with DB saving
	public Recipe() {}
	
	public Recipe(String recipeName) {
		this.setRecipeName(recipeName);
	}
	
	public Recipe(String uuid, String recipeName) {
		this.uuid = uuid;
		this.setRecipeName(recipeName);
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public Map<Ingredient, Amount> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<Ingredient, Amount> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredient(final Ingredient ingredient, final Amount amount) {
		this.ingredients.put( ingredient, amount );
	}

	@Override
	public String toString() {
		return "{ uuid:" + uuid + "}";
	}
}
