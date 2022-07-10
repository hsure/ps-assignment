package com.hari.DriverShipments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(DriverShipmentsApplication.class);


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

	/**
	 * Load the destinations and drivers from the two files provided
	 * Apply driver assigner algorithm on destinations and drivers
	 * Print the Destination and Driver pairs
	 * Print the Total Suitability Score
	 */
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
				
				logger.info("\n\n\n\n");
				logger.info("##############  DESTINATION-DRIVER MAP  ##############");
				logger.info("\n");
				
				for(Map.Entry<Destination, Driver> entry: driverAssigner.mapDrivers(destinations, drivers).entrySet()) {
					logger.info("DESTINATION: "+entry.getKey().getAddress());
					logger.info("DRIVER: "+entry.getValue().getName()+"\n");
					totalSuitabilityScore += entry.getKey().getSuitabilityScore();
				}
				
				logger.info("\n");
				logger.info("##############  TOTAL SUITABILITY SCORE  ##############");
				logger.info("\n");
				logger.info("Total Suitability Score - "+totalSuitabilityScore);
				
			} catch(IOException e) {
				logger.info("Error while reading from file "+ e.getMessage());
			} 
			
		} else {
			logger.info("Please check the commmand format: ");
			logger.info("java -jar target/DriverShipments-0.0.1-SNAPSHOT.jar <destinations-file> <drivers-file> ");
		}
		
	}

}
