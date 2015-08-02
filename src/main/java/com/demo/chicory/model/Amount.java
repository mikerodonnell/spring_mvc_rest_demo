package com.demo.chicory.model;

public class Amount {

	private Integer quanity; // ex: 3
	private String unit; // ex: "spoonfulls"  // TODO: emumerate this!
	
	
	public Amount() {}
	
	public Amount(Integer quanity, String unit) {
		this.quanity = quanity;
		this.unit = unit;
	}
	
	public Integer getQuanity() {
		return quanity;
	}
	public void setQuanity(Integer quanity) {
		this.quanity = quanity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quanity == null) ? 0 : quanity.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Amount))
			return false;
		Amount other = (Amount) obj;
		if (quanity == null) {
			if (other.quanity != null)
				return false;
		} else if (!quanity.equals(other.quanity))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	
}

