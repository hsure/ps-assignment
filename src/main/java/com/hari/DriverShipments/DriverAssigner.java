package com.hari.DriverShipments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * Contains the core solution to the problem i.e to mapping drivers to the shipping destinations
 * while maximizing the suitability score. To achieve that
 *
 * - We create a matrix(scoreMatrix) of nxn size where each row represents a shipping destination and each column represents a driver
 *   Each value in the matrix is suitability score of shipping destination if the driver representing the column delivers the shipment
 * - Core problem is an Assignment Problem but for maximization. To convert it into a minimization problem, we convert the matrix by
 *   replacing every value in the matrix with the absolute value after subtracting the value with maximum number in the matrix
 * - After getting the converted matrix ready, we apply Hungarian algorithm to solve the Assignment problem
 * - Result of Hungarian algorithm is list of drivers assigned to a destination
 * - While iterating through the result of Hungarian algorithm, we populate the map with Destination and the driver
 *
 * @author hsure
 *
 */
@Component
public class DriverAssigner {

  /**
   * 1. Create scoreMatrix to apply Hungarian algorithm for assignment
   * 2. Convert it into a minimization problem
   * 3. Iterating through the result of Hungarian algorithm map the destination to the driver
   *
   * @param destinations
   * @param drivers
   * @return Final mapping of each destination with a driver resulting in maximum SS
   */
  public Map<Destination, Driver> mapDrivers(
    List<Destination> destinations,
    List<Driver> drivers
  ) {
    Map<Destination, Driver> map = new HashMap<Destination, Driver>();

    double[][] scoreMatrix = createScoreMatrix(destinations, drivers);
    double[][] minimizeMatrix = convertToMinimizeProblem(scoreMatrix);

    HungarianAlgorithm algorithm = new HungarianAlgorithm(minimizeMatrix);
    int[] result = algorithm.execute();

    for (int i = 0; i < result.length; i++) {
      map.put(destinations.get(i), drivers.get(result[i]));
      destinations.get(i).setSuitabilityScore(scoreMatrix[i][result[i]]);
    }

    return map;
  }

  /**
   * Creates the scoreMatrix, a nxn matrix, where
   * 		Each row represents a destination and
   * 		Each column represents a driver and
   * 		Each value represents suitability score for the destination if the driver delivers the shipment
   *
   * @param destinations
   * @param drivers
   * @return nxn matrix
   */
  private double[][] createScoreMatrix(
    List<Destination> destinations,
    List<Driver> drivers
  ) {
    int size = destinations.size();
    double[][] matrix = new double[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Set<Integer> intersection = new HashSet<>(destinations.get(i).getFactors());
        intersection.retainAll(drivers.get(j).getFactors());
        if (intersection.size() > 1) {
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

  /**
   * Takes the original scoreMatrix and converts it into a minimization problem by subtracting
   * the maximum value in the matrix from every value and replacing it with the absolute difference
   *
   * @param matrix
   * @return
   */
  private double[][] convertToMinimizeProblem(double[][] matrix) {
    double max = 0.0;
    int size = matrix.length;
    double[][] newMatrix = new double[size][];

    for (int i = 0; i < matrix.length; i++) {
      newMatrix[i] = matrix[i].clone();
    }

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (newMatrix[i][j] > max) max = newMatrix[i][j];
      }
    }

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        newMatrix[i][j] = Math.abs(max - newMatrix[i][j]);
      }
    }

    return newMatrix;
  }
}
