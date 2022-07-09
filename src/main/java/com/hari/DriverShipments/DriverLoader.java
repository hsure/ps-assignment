package com.hari.DriverShipments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class DriverLoader {

	
	public List<Driver> loadDrivers(String file) throws IOException {
		List<String> drivers = Files.readAllLines(Paths.get(file));
		
		return drivers.stream()
				.map(s -> new Driver(s)).collect(Collectors.toList());
	}
}
