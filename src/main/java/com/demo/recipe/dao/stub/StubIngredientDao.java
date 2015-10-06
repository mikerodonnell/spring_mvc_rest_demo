package com.demo.recipe.dao.stub;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.demo.recipe.dao.IngredientDao;
import com.demo.recipe.model.Ingredient;

@Repository
public class StubIngredientDao implements IngredientDao {
	
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
	
	
	@Override
	public Set<Ingredient> getIngredients() {
		return ALL_INGREDIENTS;
	}
	
	@Override
	public Ingredient getIngredient( final String name ) {
		for( Ingredient ingredient : ALL_INGREDIENTS ) {
			if( ingredient.getName().equalsIgnoreCase(name) )
				return ingredient;
		}
		
		return null;
	}
	
	@Override
	public Ingredient saveIngredient( final Ingredient newIngredient ) {
		// just a stub ... real implementation would persist here
		return newIngredient;
	}
	
}
