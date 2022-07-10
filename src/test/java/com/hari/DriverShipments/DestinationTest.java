package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DestinationTest {
	
	private Destination destination;
	
	@Test
	void testDestinationPropertiesUponCreation() {
		String address = "test street, test city, TA 00000";
		

		destination = new Destination(address);
		assertThat(destination).isNotNull();
		assertThat(destination.getAddress()).isEqualTo(address);
		assertThat(destination.getSuitabilityScore()).isEqualTo(7.0);
	}

}
