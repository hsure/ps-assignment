package com.hari.DriverShipments;

import java.util.Arrays;
import java.util.Set;


/**
 * Destination data object
 * 
 * @param address of the shipping destination
 * 
 * - Generates the list of all the factors of the length of the street address
 * - Generates the suitability score per the top-secret algorithm
 * 		- If the length of the shipment's destination street name is even, the base suitability score (SS) is the number of vowels in the driver’s name multiplied by 1.5.
 * 		- If the length of the shipment's destination street name is odd, the base SS is the number of consonants in the driver’s name multiplied by 1.
 * 
 * 
 * @author hsure
 *
 */
public class Destination {
	
	private final String address;
	private Set<Integer> factors;
	private Double suitabilityScore;
	
	public Destination(String address) {
		this.address = address;
		this.factors = ScoreUtils.getFactors(this.address);
		this.suitabilityScore = this.createSuitabilityScore(this.address);
	}
	
	/**
	 * 
	 * @param address
	 * 	1. Extracts length of the street address in the shipping destination 
	 *  2. Counts vowels and consonants in the street address
	 *  3. If even, create suitability score as vowelCount * 1.5
	 *     If odd, create suitability score as consonantCount * 1.0
	 * 
	 * @return SuitabilityScore of the destination
	 */
	private Double createSuitabilityScore(String address) {
		//Assuming a valid address. Ideally validations should be in place.
		int len = address.split(",")[0].length();
		Set<Character> vowels = ScoreUtils.getVowelSet();
		
		int vowelCount = 0;
		int consonantCount = 0;
		
		for(int i=0;i<len;i++) {
			char ch = address.charAt(i);
			if(Character.isAlphabetic(ch)) {
				if(vowels.contains(ch)) vowelCount++;
				else consonantCount++;
			}
		}
		
		if(len%2 == 0) {
			return vowelCount * 1.5;
		} else {
			return consonantCount * 1.0;
		}
	}
	
	public Set<Integer> getFactors() {
		return factors;
	}
	
	public Double getSuitabilityScore() {
		return this.suitabilityScore;
	}
	
	public void setSuitabilityScore(Double score) {
		this.suitabilityScore = score;
	}

	public String getAddress() {
		return address;
	}
}
