// src/main/java/com/example/positiontracker/PositionTrackerApplication.java
package com.example.positiontracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController // Make this class a REST controller
public class PositionTrackerApplication {

    // In-memory store for vehicle positions (simple for now)
    private final Map<String, String> vehiclePositions = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(PositionTrackerApplication.class, args);
    }

    /**
     * Health check endpoint for Kubernetes liveness/readiness probes.
     * @return A simple string indicating health.
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    /**
     * Receives vehicle position updates.
     * @param vehicleName The name of the vehicle.
     * @param position The position string (e.g., "lat,long").
     * @return A confirmation message.
     */
    @PostMapping("/positions/{vehicleName}")
    public String updatePosition(@PathVariable String vehicleName, @RequestBody String position) {
        System.out.println("Received update for " + vehicleName + ": " + position);
        vehiclePositions.put(vehicleName, position);
        return "Position for " + vehicleName + " updated to " + position;
    }

    /**
     * Retrieves the latest position for a specific vehicle.
     * @param vehicleName The name of the vehicle.
     * @return The latest position.
     */
    @GetMapping("/positions/{vehicleName}")
    public String getPosition(@PathVariable String vehicleName) {
        return vehiclePositions.getOrDefault(vehicleName, "Vehicle not found");
    }

    /**
     * Retrieves all known vehicle positions.
     * @return A map of all vehicle positions.
     */
    @GetMapping("/positions")
    public Map<String, String> getAllPositions() {
        return vehiclePositions;
    }
}