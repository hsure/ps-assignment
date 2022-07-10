package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class DriverLoaderTest {

	private DriverLoader driverLoader;
	
	@Test
	void testDriverLoader() throws IOException {
		String fileName = "src/test/resources/testdriver.txt";
		String driver = "test driver";
		
		driverLoader = new DriverLoader();
		List<Driver> drivers = driverLoader.loadDrivers(fileName);
		
		assertThat(drivers).isNotNull();
		assertThat(drivers.size()).isEqualTo(1);
		assertEquals(drivers.get(0).getName(), driver);
	}

}
