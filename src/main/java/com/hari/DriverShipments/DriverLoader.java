package com.hari.DriverShipments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Driver loader helps with loading the driver names from the file.
 *
 * @return List of all the driver objects
 *
 * @author hsure
 *
 */
@Component
public class DriverLoader {

  public List<Driver> loadDrivers(String file) throws IOException {
    List<String> drivers = Files.readAllLines(Paths.get(file));

    return drivers.stream().map(Driver::new).toList();
  }
}
