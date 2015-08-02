package com.demo.chicory.stub.dao;

import java.util.HashSet;
import java.util.Set;

import com.demo.chicory.model.Ingredient;

public class StubIngredientDao {

	
	public static final Ingredient HAM = new Ingredient("ham");
	public static final Ingredient EGGS = new Ingredient("eggs");
	public static final Ingredient SUGAR = new Ingredient("sugar");
	
	public static final Set<Ingredient> ALL_INGREDIENTS = new HashSet<Ingredient>();
	static {
		ALL_INGREDIENTS.add(HAM);
		ALL_INGREDIENTS.add(EGGS);
		ALL_INGREDIENTS.add(SUGAR);
		// TODO: unmodifiableSet
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
