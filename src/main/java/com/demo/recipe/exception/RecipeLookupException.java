package com.demo.recipe.exception;

public class RecipeLookupException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public RecipeLookupException() {}
	
	public RecipeLookupException( String message ) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
