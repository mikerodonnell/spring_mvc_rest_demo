package com.demo.recipe.service;

import com.demo.recipe.exception.RecipeCreationException;
import com.demo.recipe.model.Amount;
import com.demo.recipe.model.Ingredient;
import com.demo.recipe.model.Recipe;
import com.demo.recipe.serialization.RecipeRepresentation;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    private static final RecipeRepresentation extantRecipeRepresentation = new RecipeRepresentation();
    private static final RecipeRepresentation newRecipeRepresentation = new RecipeRepresentation();

    @BeforeClass
    public static void setupClass() {
        extantRecipeRepresentation.setRecipeName("green eggs and ham");
        extantRecipeRepresentation.setAmount1(4);
        extantRecipeRepresentation.setAmountUnit1("ounce");
        extantRecipeRepresentation.setIngredientName1("ham");

        newRecipeRepresentation.setRecipeName("tomato soup");
        newRecipeRepresentation.setAmount1(1);
        newRecipeRepresentation.setAmountUnit1("pinch");
        newRecipeRepresentation.setIngredientName1("salt");
    }

    @Test
    public void testCreateRecipe() {
        Recipe recipe = recipeService.createRecipe(newRecipeRepresentation);
        assertEquals(newRecipeRepresentation.getRecipeName(), recipe.getRecipeName());

        // the recipe name checks out, now pull the Ingredients object off the Recipe and check that too
        Map<Ingredient, Amount> ingredients = recipe.getIngredients();
        Ingredient ingredient = ingredients.keySet().iterator().next();
        assertEquals(newRecipeRepresentation.getIngredientName1(), ingredient.getName());
        assertEquals(newRecipeRepresentation.getAmount1(), ingredients.get(ingredient).getQuanity());
        assertEquals(newRecipeRepresentation.getAmountUnit1(), ingredients.get(ingredient).getUnit());
    }

    @Test(expected = RecipeCreationException.class)
    public void testCreateRecipeAlreadyExists() {
        recipeService.createRecipe(extantRecipeRepresentation);
    }

    @Test(expected = RecipeCreationException.class)
    public void testCreateRecipeNull() {
        recipeService.createRecipe(null);
    }
}
