package com.hari.DriverShipments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DriverShipmentsApplication implements CommandLineRunner {

	@Autowired
	private DestinationLoader destinationLoader;
	
	@Autowired
	private DriverLoader driverLoader;
	
	@Autowired
	private DriverAssigner driverAssigner;
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(DriverShipmentsApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length == 2) {
			String destinationsFile = args[0];
			String driversFile = args[1];
			
			try {
				List<Destination> destinations = destinationLoader.loadDestinations(destinationsFile);
				List<Driver> drivers = driverLoader.loadDrivers(driversFile);
				
				Double totalSuitabilityScore = 0.0;
				
				System.out.println("\n\n\n\n");
				System.out.println("##############  DESTINATION-DRIVER MAP  ##############");
				System.out.println("\n");
				
				for(Map.Entry<Destination, Driver> entry: driverAssigner.mapDrivers(destinations, drivers).entrySet()) {
					System.out.println("DESTINATION: "+entry.getKey().getAddress());
					System.out.println("DRIVER: "+entry.getValue().getName()+"\n");
					totalSuitabilityScore += entry.getKey().getSuitabilityScore();
				}
				
				System.out.println("\n");
				System.out.println("##############  TOTAL SUITABILITY SCORE  ##############");
				System.out.println("\n");
				System.out.println("Total Suitability Score - "+totalSuitabilityScore);
				
			} catch(IOException e) {
				System.out.println("Error while reading from file "+ e.getMessage());
			} catch(Exception e) {
				System.out.println("Error while processing "+ e.getMessage());
			}
			
		} else {
			
		}
		
	}

}
