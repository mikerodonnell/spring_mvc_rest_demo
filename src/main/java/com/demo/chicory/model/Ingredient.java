package com.demo.chicory.model;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Ingredient {

	private String name;
	
	@Deprecated
	private String priceUnit;
	
	@Deprecated
	private Integer priceDollars; // US dollars per priceUnit

	@JsonIgnore
	private Set<Price> prices = new HashSet<Price>(); // TODO: finish implementing this...
	
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
