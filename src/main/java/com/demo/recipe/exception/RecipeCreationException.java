package com.demo.recipe.exception;

public class RecipeCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public RecipeCreationException() {}
	
	public RecipeCreationException( String message ) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
