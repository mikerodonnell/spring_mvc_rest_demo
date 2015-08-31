package com.demo.chicory.model;

public class Ingredient {

	/** The specific distinguishable name of the ingredient: ex: "canned corn" */
	private String name;
	
	/** The unit of measurement comprising an amount of an ingredient. ex: "spoonfulls" 
	 * @see Amount#getUnit()
	 * @todo make this an enum that Ingredient and Amount use
	 */
	private String priceUnit;
	
	/** The price in US Dollars for one unit of this ingredient */
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
