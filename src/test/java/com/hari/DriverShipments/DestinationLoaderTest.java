package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class DestinationLoaderTest {

  private DestinationLoader destinationLoader;

  @Test
  void testDriverLoader() throws IOException {
    String fileName = "src/test/resources/testdestinations.txt";
    String destination = "test street, test city, TA 00000";

    destinationLoader = new DestinationLoader();
    List<Destination> destinations = destinationLoader.loadDestinations(fileName);

    assertThat(destinations).isNotNull().hasSize(1);
    assertEquals(destinations.get(0).getAddress(), destination);
  }
}
