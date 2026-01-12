package com.jake.fitnessapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Simple root endpoint so visiting http://localhost:8080/ doesn't throw an error
@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Fitness API is running. Try /health or /workouts";
    }
}
