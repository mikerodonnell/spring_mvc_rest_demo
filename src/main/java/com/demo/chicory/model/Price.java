package com.demo.chicory.model;

public class Price {

	private String priceUnit;
	private Integer priceDollars; // US dollars per priceUnit
	
	
	public Price() {}
	
	public Price( String priceUnit, Integer priceDollars ) {
		this.priceUnit  = priceUnit;
		this.priceDollars = priceDollars;
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
	public void setPriceDollars(Integer priceDollars) {
		this.priceDollars = priceDollars;
	}
}
