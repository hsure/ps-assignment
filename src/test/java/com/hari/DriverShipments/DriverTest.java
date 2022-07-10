package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DriverTest {

	private Driver driver;
	
	@Test
	void testDestinationPropertiesUponCreation() {
		String name = "test driver";
		

		driver = new Driver(name);
		assertThat(driver).isNotNull();
		assertThat(driver.getName()).isEqualTo(name);
	}

}
