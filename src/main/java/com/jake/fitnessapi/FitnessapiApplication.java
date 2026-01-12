/* Created: 4 Jan 2026
    Last updated: 12 Jan 2026 */

package com.jake.fitnessapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// Main entry point for the Spring Boot application
@SpringBootApplication
// note: forces Spring to detect @Entity classes
@EntityScan("com.jake.fitnessapi.model") 
public class FitnessapiApplication {

    // Starts the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(FitnessapiApplication.class, args);
    }
}