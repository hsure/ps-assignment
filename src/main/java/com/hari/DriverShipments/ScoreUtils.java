package com.hari.DriverShipments;

import java.util.HashSet;
import java.util.Set;

public class ScoreUtils {

  public static Set<Integer> getFactors(String someString) {
    int len = someString.length();
    Set<Integer> factors = new HashSet<>();

    for (int i = 1; i <= Math.sqrt(len); i++) {
      if (len % i == 0) {
        if (len / i == i) {
          factors.add(i);
        } else {
          factors.add(i);
          factors.add(len / i);
        }
      }
    }

    return factors;
  }

  public static Set<Character> getVowelSet() {
    Set<Character> vowelSet = new HashSet<>();
    vowelSet.add('a');
    vowelSet.add('e');
    vowelSet.add('i');
    vowelSet.add('o');
    vowelSet.add('u');
    vowelSet.add('A');
    vowelSet.add('E');
    vowelSet.add('I');
    vowelSet.add('O');
    vowelSet.add('U');

    return vowelSet;
  }
}
