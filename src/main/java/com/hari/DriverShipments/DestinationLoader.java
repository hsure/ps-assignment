package com.hari.DriverShipments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


/**
 * Destination loader helps with loading the shipping destination addresses from the file.
 * 
 * @return List of all the destination objects
 * 
 * @author hsure
 * 
 */
@Component
public class DestinationLoader {

	public List<Destination> loadDestinations(String file) throws IOException {
		List<String> destinations = Files.readAllLines(Paths.get(file));
		
		return destinations.stream()
				.map(s -> new Destination(s)).collect(Collectors.toList());
	}
}
