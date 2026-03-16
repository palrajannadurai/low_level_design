package com.parkinglot.vehicles;

import com.parkinglot.enums.VehicleType;

/**
 * ABSTRACT CLASS: Vehicle
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS:
 * 1. Abstraction: Defines contract for ALL vehicles entering parking lot
 * 2. Code Reuse: Common properties (license, type) defined once
 * 3. Polymorphism: Parking lot can work with any vehicle type
 *
 * DESIGN PRINCIPLE: Open/Closed - new vehicle types don't modify existing code
 * PATTERN: Template Pattern - common structure with extension points
 * ----------------------------------------------------------------------------
 */
public abstract class Vehicle {
    // WHY PRIVATE: Encapsulation - protect data from direct modification
    private final String licenseNumber;
    private final VehicleType type;
    private final String color;
    private final String make;

    /**
     * WHY CONSTRUCTOR EXISTS: Ensures every vehicle MUST have license and type
     * No default constructor - can't create vehicle without essential info
     */
    protected Vehicle(String licenseNumber, VehicleType type, String color, String make) {
        // Validation: Business rule - license plate required
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number is required");
        }
        this.licenseNumber = licenseNumber;
        this.type = type;
        this.color = color;
        this.make = make;
    }

    // WHY GETTERS EXIST: Controlled access to data
    // No setters - vehicle properties don't change after creation (Immutability)
    public String getLicenseNumber() { return licenseNumber; }
    public VehicleType getType() { return type; }
    public String getColor() { return color; }
    public String getMake() { return make; }

    /**
     * WHY THIS METHOD EXISTS: Different vehicles might need special services
     * Hook method for subclasses to override
     */
    public boolean needSpecialHandling() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", type.getDisplayName(), licenseNumber);
    }
}
