package com.demo.recipe.dao;

import com.demo.recipe.model.Ingredient;

public interface IngredientDao {
    Ingredient getIngredient(String name);

    Ingredient saveIngredient(Ingredient newIngredient);
}
