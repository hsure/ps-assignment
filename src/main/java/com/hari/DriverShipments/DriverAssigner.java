package com.hari.DriverShipments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class DriverAssigner {
	
	public Map<Destination, Driver> mapDrivers(List<Destination> destinations, List<Driver> drivers) {
		Map<Destination, Driver> map = new HashMap<Destination, Driver>();
		
		double[][] scoreMatrix = createScoreMatrix(destinations, drivers);
		double[][] minimizeMatrix = convertToMinimizeProblem(scoreMatrix);
		
		HungarianAlgorithm algorithm = new HungarianAlgorithm(minimizeMatrix);
		int[] result = algorithm.execute();
		
		for(int i=0;i<result.length;i++) {
			map.put(destinations.get(i), drivers.get(result[i]));
			destinations.get(i).setSuitabilityScore(scoreMatrix[i][result[i]]);
		}
		
		return map;
	}
	
	private double[][] createScoreMatrix(List<Destination> destinations, List<Driver> drivers) {
		int size = destinations.size();
		double[][] matrix = new double[size][size];
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				Set<Integer> intersection = new HashSet<>(destinations.get(i).getFactors());
				intersection.retainAll(drivers.get(j).getFactors());
				if(intersection.size() > 1) {
					matrix[i][j] = destinations.get(i).getSuitabilityScore() * 1.5;
					//System.out.println("destination: "+destinations.get(i).getAddress()+ "Destination Factors: " + Arrays.toString(destinations.get(i).getFactors().toArray()) + "Driver Factors: "+ Arrays.toString(drivers.get(j).getFactors().toArray()) + "Driver: " + drivers.get(j).getName() +" score: "+matrix[i][j]);
				} else {
					matrix[i][j] = destinations.get(i).getSuitabilityScore();
					//System.out.println("destination: "+destinations.get(i).getAddress()+ "Destination Factors: " + Arrays.toString(destinations.get(i).getFactors().toArray()) + "Driver Factors: "+ Arrays.toString(drivers.get(j).getFactors().toArray()) + "Driver: " + drivers.get(j).getName() +" score: "+matrix[i][j]);
				}
			}
		}
		
		return matrix;
	}
	
	private double[][] convertToMinimizeProblem(double[][] matrix) {
		double max = 0.0;
		int size = matrix.length;
		double[][] newMatrix = new double[size][];
		
		for(int i=0;i<matrix.length;i++) {
			newMatrix[i] = matrix[i].clone();
		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(newMatrix[i][j] > max) max = newMatrix[i][j];
			}
		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				newMatrix[i][j] = Math.abs(max - newMatrix[i][j]);
			}
		}
		
		return newMatrix;
	}

}
