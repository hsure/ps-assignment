package com.hari.DriverShipments;

import java.util.Set;

public class Driver {
	
	private String name;
	private Set<Integer> factors;
	
	public Driver(String name) {
		setName(name);
		this.factors = ScoreUtils.getFactors(name);
	}
	
	public void setFactors(Set<Integer> factors) {
		this.factors = factors;
	}
	
	public Set<Integer> getFactors() {
		return factors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
