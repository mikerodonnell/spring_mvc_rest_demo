package com.demo.recipe.stub.dao;

import java.util.HashSet;
import java.util.Set;

import com.demo.recipe.model.Ingredient;

public class StubIngredientDao {
	
	public static final Ingredient HAM = new Ingredient("ham");
	public static final Ingredient EGGS = new Ingredient("eggs");
	public static final Ingredient SUGAR = new Ingredient("sugar");
	
	public static final Set<Ingredient> ALL_INGREDIENTS = new HashSet<Ingredient>();
	static {
		HAM.setPriceUnit("lb");
		HAM.setPriceDollars(6);
		
		EGGS.setPriceUnit("dozen");
		EGGS.setPriceDollars(2);
		
		SUGAR.setPriceUnit("lb");
		SUGAR.setPriceDollars(4);
		
		ALL_INGREDIENTS.add(HAM);
		ALL_INGREDIENTS.add(EGGS);
		ALL_INGREDIENTS.add(SUGAR);
	}
	
	
	public static Set<Ingredient> getIngredients() {
		return ALL_INGREDIENTS;
	}
	
	public static Ingredient getIngredient( final String name ) {
		for( Ingredient ingredient : ALL_INGREDIENTS ) {
			if( ingredient.getName().equalsIgnoreCase(name) )
				return ingredient;
		}
		
		return null;
	}
	
	public static Ingredient saveIngredient( final Ingredient newIngredient ) {
		// just a stub ... real implementation would persist here
		return newIngredient;
	}
	
}
