package com.demo.recipe.service;

import com.demo.recipe.model.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class IngredientServiceTest {

    @Autowired
    private IngredientService ingredientService;

    @Test
    public void testCreateIngredient() {
        final String ingredientName = "ham";
        Ingredient ingredient = ingredientService.createIngredient(ingredientName);

        assertEquals(ingredientName, ingredient.getName());
        assertTrue(ingredientService.ingredientExists(ingredient));
    }

    @Test
    public void testIngredientExistsNull() {
        assertFalse(ingredientService.ingredientExists(null)); // verify false returned, no exceptions thrown
    }
}
