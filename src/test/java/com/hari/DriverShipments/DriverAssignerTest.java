package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DriverAssignerTest {

  private DriverAssigner driverAssigner;

  @Test
  void test3DriverAssigner() throws IOException {
    String destinationFile = "src/test/resources/3destinations.txt";
    String driverFile = "src/test/resources/3drivers.txt";
    driverAssigner = new DriverAssigner();

    DriverLoader driverLoader = new DriverLoader();
    List<Driver> drivers = driverLoader.loadDrivers(driverFile);

    DestinationLoader destinationLoader = new DestinationLoader();
    List<Destination> destinations = destinationLoader.loadDestinations(destinationFile);

    Map<Destination, Driver> map = driverAssigner.mapDrivers(destinations, drivers);

    assertThat(map).hasSize(3);
    assertThat(
      map.keySet().stream().map(a -> a.getSuitabilityScore()).reduce(0.0, Double::sum)
    )
      .isEqualTo(20.5);
  }
}
