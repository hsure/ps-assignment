package com.hari.DriverShipments;

import java.util.Set;


/**
 * Driver data object that holds driver's name and factors of length of the driver's name
 * 
 * @author hsure
 *
 */
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
