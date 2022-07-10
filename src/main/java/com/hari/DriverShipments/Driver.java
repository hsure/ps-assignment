package com.hari.DriverShipments;

import java.util.Set;

public class Driver {
	
	private final String name;
	private Set<Integer> factors;
	
	public Driver(String name) {
		this.name = name;
		this.factors = ScoreUtils.getFactors(name);
	}
	
	public Set<Integer> getFactors() {
		return factors;
	}

	public String getName() {
		return name;
	}
	
	
}
