package com.rout.travels.itinerary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.apache.log4j.Logger;
/*
 * Boot application kickstart point
 */
@Import(JpaConfiguration.class)
@SpringBootApplication
public class Application {

	private static Logger logger = Logger.getLogger(Application.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("Itinerary Rest Apis starting");
		SpringApplication.run(Application.class, args);
		

	}

}
