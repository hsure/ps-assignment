package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DestinationTest {

	private String address; 
	
	private Destination destination;
	
	@Test
	void testDestinationPropertiesUponCreation() {
		this.address = "test street, test city, TA 00000";

		destination = new Destination(this.address);
		assertThat(destination).isNotNull();
		assertThat(destination.getAddress()).isEqualTo(this.address);
		//assertThat(destination.getFactors()).
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
