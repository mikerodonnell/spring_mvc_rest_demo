package com.demo.chicory.model;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

	private String uuid;
	private String recipeName;
	private Integer prepMinutes; // TODO: abstract out Time model to represent 3 minutes, or 5 hours, etc. 
	private Integer cookMinutes;
	private Integer servingSizeOunces; // TODO: abstract out ServingSize model for 2 grams, 3 ounces, 1 jar, etc.
	private String instructions; // TODO: this would be rich formatted data of some sort...or at least a List of steps
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

	public Integer getPrepMinutes() {
		return prepMinutes;
	}

	public void setPrepMinutes(Integer prepMinutes) {
		this.prepMinutes = prepMinutes;
	}

	public Integer getCookMinutes() {
		return cookMinutes;
	}

	public void setCookMinutes(Integer cookMinutes) {
		this.cookMinutes = cookMinutes;
	}

	public Integer getServingSizeOunces() {
		return servingSizeOunces;
	}

	public void setServingSizeOunces(Integer servingSizeOunces) {
		this.servingSizeOunces = servingSizeOunces;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
	
	public Integer getCostInDollars() {
		Integer costInDollars = 0;
		
		for( Ingredient ingredient : ingredients.keySet() ) {
			Integer unitPrice = ingredient.getPriceDollars();
			Amount amountNeeded = ingredients.get(ingredient);
			
			if( amountNeeded.getUnit().equalsIgnoreCase(ingredient.getPriceUnit()) )
				costInDollars += ( unitPrice * amountNeeded.getQuanity() );
			// else we don't know what the price is per this unit right now ... see TODO in Ingredient model
		}
		
		return costInDollars;
	}
	// no setter!
	
	@Override
	public String toString() {
		return "{ uuid:" + uuid + "}";
	}
}
