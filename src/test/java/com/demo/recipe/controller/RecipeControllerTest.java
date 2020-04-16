package com.demo.recipe.controller;

import com.demo.recipe.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.demo.recipe.RecipeTestData.EXTANT_RECIPE_UUID;
import static com.demo.recipe.RecipeTestData.NONEXISTANT_RECIPE_UUID;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class RecipeControllerTest {

    @Autowired
    private RecipeController recipeController;

    @Test
    public void testGetRecipe() {
        ResponseEntity<?> response = recipeController.getRecipe(EXTANT_RECIPE_UUID);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Recipe returnedRecipe = (Recipe) response.getBody();
        assertEquals(EXTANT_RECIPE_UUID, returnedRecipe.getUuid());
    }

    @Test
    public void testGetRecipeNonexistant() {
        ResponseEntity<?> response = recipeController.getRecipe(NONEXISTANT_RECIPE_UUID);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
