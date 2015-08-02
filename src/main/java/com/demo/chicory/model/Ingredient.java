package com.demo.chicory.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingredient {

	@JsonProperty("name") // TODO: why is this class serializing with the class reference!?
	private String name;

	public Ingredient() {}
	
	public Ingredient(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object otherObject) {
		if(otherObject instanceof Ingredient) {
			Ingredient otherIngredient = (Ingredient) otherObject;
			if( this.name != null )
				return this.name.equalsIgnoreCase(otherIngredient.getName());
		}
		
		return false;
	}
	
}
