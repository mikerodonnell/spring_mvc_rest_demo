package com.demo.recipe.serialization;

import com.demo.recipe.model.Amount;
import com.demo.recipe.model.Ingredient;
import com.demo.recipe.model.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * recipe representation helper object for incoming requests to make a new Recipe.
 *
 * @author modonnell
 * @see Recipe
 */
public class RecipeRepresentation {

    /**
     * Flag for whether {@link #ingredients} has been populated from the flattened values or not.
     *
     * @see #instructions
     */
    @JsonIgnore
    private boolean isInitialized = false;

    /**
     * All ingredients to prepare this Recipe, represented as an Amount value for each Ingredient key.
     */
    @JsonIgnore
    private Map<Ingredient, Amount> ingredients = new HashMap<>();

    public Map<Ingredient, Amount> getIngredients() {
        if (!isInitialized) {
            if (ingredientName1 != null && !ingredientName1.isEmpty() && amountUnit1 != null && !amountUnit1.isEmpty() && amount1 != null) {
                ingredients.put(new Ingredient(ingredientName1), new Amount(amount1, amountUnit1));
            }

            if (ingredientName2 != null && !ingredientName2.isEmpty() && amountUnit2 != null && !amountUnit2.isEmpty() && amount2 != null) {
                ingredients.put(new Ingredient(ingredientName2), new Amount(amount2, amountUnit2));
            }

            if (ingredientName3 != null && !ingredientName3.isEmpty() && amountUnit3 != null && !amountUnit3.isEmpty() && amount3 != null) {
                ingredients.put(new Ingredient(ingredientName3), new Amount(amount3, amountUnit3));
            }

            isInitialized = true;
        }

        return ingredients;
    }
    // no setter!

    private String recipeName;
    private Integer prepMinutes;
    private Integer cookMinutes;
    private Integer servingSizeOunces;
    private String instructions;

    // supporting up to 3 ingredients for now, could make this unlimited by parsing a CSV string or something.

    private String ingredientName1; // ex: "ham"
    private String ingredientName2;
    private String ingredientName3;

    private String amountUnit1; // ex: "teaspoon"
    private String amountUnit2;
    private String amountUnit3;
    private String amountUnit4;
    private String amountUnit5;

    private Integer amount1; // ex: 4
    private Integer amount2;
    private Integer amount3;
    private Integer amount4;
    private Integer amount5;

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

    public String getIngredientName1() {
        return ingredientName1;
    }

    public void setIngredientName1(String ingredientName1) {
        this.ingredientName1 = ingredientName1;
    }

    public String getIngredientName2() {
        return ingredientName2;
    }

    public void setIngredientName2(String ingredientName2) {
        this.ingredientName2 = ingredientName2;
    }

    public String getIngredientName3() {
        return ingredientName3;
    }

    public void setIngredientName3(String ingredientName3) {
        this.ingredientName3 = ingredientName3;
    }

    public String getAmountUnit1() {
        return amountUnit1;
    }

    public void setAmountUnit1(String amountUnit1) {
        this.amountUnit1 = amountUnit1;
    }

    public String getAmountUnit2() {
        return amountUnit2;
    }

    public void setAmountUnit2(String amountUnit2) {
        this.amountUnit2 = amountUnit2;
    }

    public String getAmountUnit3() {
        return amountUnit3;
    }

    public void setAmountUnit3(String amountUnit3) {
        this.amountUnit3 = amountUnit3;
    }

    public String getAmountUnit4() {
        return amountUnit4;
    }

    public void setAmountUnit4(String amountUnit4) {
        this.amountUnit4 = amountUnit4;
    }

    public String getAmountUnit5() {
        return amountUnit5;
    }

    public void setAmountUnit5(String amountUnit5) {
        this.amountUnit5 = amountUnit5;
    }

    public Integer getAmount1() {
        return amount1;
    }

    public void setAmount1(Integer amount1) {
        this.amount1 = amount1;
    }

    public Integer getAmount2() {
        return amount2;
    }

    public void setAmount2(Integer amount2) {
        this.amount2 = amount2;
    }

    public Integer getAmount3() {
        return amount3;
    }

    public void setAmount3(Integer amount3) {
        this.amount3 = amount3;
    }

    public Integer getAmount4() {
        return amount4;
    }

    public void setAmount4(Integer amount4) {
        this.amount4 = amount4;
    }

    public Integer getAmount5() {
        return amount5;
    }

    public void setAmount5(Integer amount5) {
        this.amount5 = amount5;
    }

    @Override
    public String toString() {
        return (new JSONObject(this)).toString();
    }
}
