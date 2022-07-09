package com.hari.DriverShipments;

import java.util.HashSet;
import java.util.Set;

public class Destination {

	private String address;
	private Set<Integer> factors;
	private Double suitabilityScore;
	
	public Destination(String address) {
		this.setAddress(address);
		this.factors = ScoreUtils.getFactors(this.address);
		this.suitabilityScore = createSuitabilityScore(this.address);
	}
	
	private Double createSuitabilityScore(String address) {
		int len = address.split(",")[0].length();
		System.out.println("length of the address - "+address+" length: "+len);
		Set<Character> vowels = ScoreUtils.getVowelSet();
		
		int vowelCount = 0;
		int consonantCount = 0;
		
		for(int i=0;i<len;i++) {
			char ch = address.charAt(i);
			if(Character.isAlphabetic(ch)) {
				if(vowels.contains(address.charAt(i))) vowelCount++;
				else consonantCount++;
			}
		}
		
		if(len%2 == 0) {
			return vowelCount * 1.5;
		} else {
			return consonantCount * 1.0;
		}
	}
	
	public Double getSuitabilityScore() {
		return this.suitabilityScore;
	}
	
	public Set<Integer> getFactors() {
		return factors;
	}
	
	public void setSuitabilityScore(Double score) {
		this.suitabilityScore = score;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		this.setFactors(this.address);
		this.createSuitabilityScore(address);
	}

	private void setFactors(String address) {
		this.factors = ScoreUtils.getFactors(address);		
	}
}
