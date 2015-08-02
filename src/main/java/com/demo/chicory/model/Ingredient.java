package com.demo.chicory.model;

// import org.codehaus.jackson.annotate.JsonProperty;

public class Ingredient {

	// @JsonProperty("name") // TODO: why is this class serializing with the class reference!?
	private String name;
	
	private String priceUnit;
	private Integer priceDollars; // US dollars per priceUnit

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


	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public Integer getPriceDollars() {
		return priceDollars;
	}

	public void setPriceDollars(Integer priceCost) {
		this.priceDollars = priceCost;
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
