package com.demo.recipe.controller;

import static com.demo.recipe.RecipeTestData.*;
import static com.jayway.restassured.RestAssured.get;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.RestAssured;

public class RecipeControllerIT {
	
	@BeforeClass
	public static void configureRestAssured() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8889;
		RestAssured.basePath = "spring_mvc_rest_demo";
	}
	
	
	@Test
	public void testGetRecipe() {
		get("recipe/" + EXTANT_RECIPE_UUID).then().assertThat().statusCode(HttpStatus.OK.value());
	}
}
