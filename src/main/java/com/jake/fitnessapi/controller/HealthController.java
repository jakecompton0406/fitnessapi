package com.jake.fitnessapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Simple controller used to verify that the application is running
@RestController
public class HealthController {

    // Health check endpoint
    // Used to confirm the API is up and responding
    @GetMapping("/health")
    public String health() {
        return "OK"; // Basic response to indicate the service is healthy
    }
}
