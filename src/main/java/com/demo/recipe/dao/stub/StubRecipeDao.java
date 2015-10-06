package com.demo.recipe.dao.stub;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.demo.recipe.dao.RecipeDao;
import com.demo.recipe.model.Amount;
import com.demo.recipe.model.Ingredient;
import com.demo.recipe.model.Recipe;

@Repository
public class StubRecipeDao implements RecipeDao {

	public static final Recipe RECIPE_1 = new Recipe("green eggs and ham");
	public static final Recipe RECIPE_2 = new Recipe("banana bread");
	public static final Recipe RECIPE_3 = new Recipe("soylent");
	
	static {
		RECIPE_1.setUuid("c5515b57-55e9-4f37-963b-dc73ec399fb8");
		RECIPE_1.setPrepMinutes(3);
		RECIPE_1.setCookMinutes(6);
		RECIPE_1.setInstructions("fry green eggs. fry ham.");
		RECIPE_1.setServingSizeOunces(8);
		RECIPE_1.addIngredient( StubIngredientDao.EGGS, new Amount(1, "dozen") );
		RECIPE_1.addIngredient( StubIngredientDao.HAM, new Amount(2, "lb") );
		
		RECIPE_2.setUuid("fbe02fec-697d-4a94-9a57-cc77b0de5a90");
		RECIPE_2.setPrepMinutes(10);
		RECIPE_2.setCookMinutes(30);
		RECIPE_2.setInstructions("mash bananas. bake.");
		RECIPE_2.setServingSizeOunces(6);
		RECIPE_2.addIngredient( StubIngredientDao.SUGAR, new Amount(1, "lb") );
		
		RECIPE_3.setUuid("4acdfafb-32b5-404c-a658-10a1619f56e6");
		RECIPE_3.setPrepMinutes(50);
		RECIPE_3.setCookMinutes(30);
		RECIPE_3.setInstructions(null);
		RECIPE_3.setServingSizeOunces(3);
	}
	
	public static final Set<Recipe> ALL_RECIPES = new HashSet<Recipe>();
	static {
		ALL_RECIPES.add(RECIPE_1);
		ALL_RECIPES.add(RECIPE_2);
		ALL_RECIPES.add(RECIPE_3);
	}
	
	@Override
	public Set<Recipe> getRecipes() {
		return ALL_RECIPES;
	}
	
	@Override
	public Recipe getRecipeByUuid( final String uuid ) {
		for( Recipe recipe : ALL_RECIPES) {
			if( recipe.getUuid().equals(uuid))
				return recipe;
		}
		
		return null;
	}
	
	@Override
	public Recipe getRecipeByName( final String recipeName ) {
		for( Recipe recipe : ALL_RECIPES) {
			if( recipe.getRecipeName().equals(recipeName))
				return recipe;
		}
		
		return null;
	}
	
	@Override
	public Recipe saveRecipe( final Recipe newRecipe ) {
		// just a stub ... real implementation would persist here
		return newRecipe;
	}
	
	@Override
	public Set<Recipe> searchRecipesByIngredientName( final String ingredientName ) {
		Set<Recipe> results = new HashSet<Recipe>();
		
		if( ingredientName!=null && !ingredientName.isEmpty() ) {
			for( Recipe recipe : ALL_RECIPES) {
				for( Ingredient ingredient : recipe.getIngredients().keySet() ) {
					if( ingredientName.toLowerCase().contains(ingredient.getName()) ) { // case sensitive for performance! TODO: do toLowerCase() on save!
						results.add( recipe );
						break; // don't keep looping over the rest of the ingredients.
					}
				}
			}
		}
		
		return results;
	}
}
