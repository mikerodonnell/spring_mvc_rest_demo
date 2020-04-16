package com.demo.recipe.controller;

import com.demo.recipe.serialization.RecipeRepresentation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.demo.recipe.RecipeTestData.EXTANT_RECIPE_UUID;
import static com.demo.recipe.RecipeTestData.NONEXISTANT_RECIPE_UUID;
import static com.demo.recipe.dao.stub.StubRecipeDao.RECIPE_1;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class RecipeControllerIT {

    @BeforeClass
    public static void configureRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8889;
        RestAssured.basePath = "spring_mvc_rest_demo";
    }

    @Test
    public void testGetRecipe() {
        get("recipe/" + RECIPE_1.getUuid()).then().assertThat().
                statusCode(HttpStatus.OK.value()).
                contentType(ContentType.JSON).
                body("uuid", equalTo(RECIPE_1.getUuid())).
                body("recipeName", equalTo(RECIPE_1.getRecipeName()));

        get("recipe/" + NONEXISTANT_RECIPE_UUID).then().assertThat().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testSearchRecipesByIngredientName() {
        get("recipe/search/ham").then().assertThat().
                statusCode(HttpStatus.OK.value()).
                contentType(ContentType.JSON).
                body("$", hasSize(1)). // $ is the placeholder for an anonymous JSON array at the root of JSON. assert that the response array has size 1 (1 recipe contains ham).
                body("uuid", hasItems(RECIPE_1.getUuid())); // assert that the response array contains the UUID of the green eggs and ham recipe

        // verify that searching for an ingredient for which there are no recipes still returns HTTP 200, and empty result set.
        get("recipe/search/salsa").then().assertThat().
                statusCode(HttpStatus.OK.value()).
                contentType(ContentType.JSON).
                body("$", empty()); // valid response, but no results. empty array.
    }

    @Test
    public void testCreate() {
        // take advantage of REST assured's built-in Content-Type based serialization, no need to build json explicitly!
        // see: https://code.google.com/p/rest-assured/wiki/Usage#Serialization
        RecipeRepresentation requestBody = new RecipeRepresentation();
        requestBody.setRecipeName("irish bread");
        requestBody.setIngredientName1("raisins");
        requestBody.setAmount1(1);
        requestBody.setAmountUnit1("cup");

        given().contentType(ContentType.JSON).body(requestBody).put("recipe/create").then().assertThat().
                statusCode(HttpStatus.OK.value()).
                contentType(ContentType.JSON).
                body("uuid", not(isEmptyOrNullString())).
                body("recipeName", equalTo("irish bread"));
    }

    @Test
    public void testCreateNoIngredients() {
        // you need at least one ingredient to create a recipe
        RecipeRepresentation requestBody = new RecipeRepresentation();
        requestBody.setRecipeName("irish bread");

        given().contentType(ContentType.JSON).body(requestBody).put("recipe/create").then().assertThat().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testCreateNullBody() {
        // providing no post data at all ultimately triggers a RecipeCreationException for no recipe name, which results in HTTP 400
        given().contentType(ContentType.JSON).put("recipe/create").then().assertThat().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testUpdate() {
        RecipeRepresentation requestBody = new RecipeRepresentation();
        requestBody.setPrepMinutes(99);

        given().contentType(ContentType.JSON).body(requestBody).put("recipe/update/" + EXTANT_RECIPE_UUID).then().assertThat().
                statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testUpdateNullBody() {
        // providing a valid recipe id to update, but no details of what to update at all, should result in HTTP 400
        given().contentType(ContentType.JSON).put("recipe/update/" + EXTANT_RECIPE_UUID).then().assertThat().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testUpdateInvalidId() {
        given().contentType(ContentType.JSON).put("recipe/update/" + NONEXISTANT_RECIPE_UUID).then().assertThat().
                statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
