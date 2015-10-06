package com.demo.recipe.model;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

	/** A unique identifier for this Recipe. ex: "b88240a2-7252-4e7c-9e74-78bb31c1fe27" */
	private String uuid;
	
	/** A descriptive name for this Recipe. ex: "green eggs and ham" */
	private String recipeName;
	
	/** The number of minutes required to prepare ingredients for this Recipe, if any. ex: 20 */
	private Integer prepMinutes;
	
	/** The number of minutes required to cook this Recipe, if any. ex: 30 */
	private Integer cookMinutes;
	
	/** The number of imperial ounces that comprise a per-person serving of this Recipe. ex: 6 */
	private Integer servingSizeOunces;
	
	/** The step-by-step instructions to prepare this Recipe. ex: "Dye eggs green. Cook eggs. Cook ham." */
	private String instructions;
	
	/** All ingredients to prepare this Recipe, represented as an Amount value for each Ingredient key. */
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
			// else we don't know what the price is per this unit.
		}
		
		return costInDollars;
	}
	// no setter!
	
	@Override
	public String toString() {
		return "{ recipeName: " + recipeName + ", uuid: " + uuid + "}";
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Recipe))
			return false;
		Recipe other = (Recipe) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
}
