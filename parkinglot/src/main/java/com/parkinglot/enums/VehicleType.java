package com.parkinglot.enums;

/**
 * ENUM: VehicleType
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS:
 * 1. Type Safety: Prevents invalid vehicle types (no more "CAR", "car", "Car" bugs)
 * 2. Central Definition: All vehicle types defined in one place
 * 3. Easy Extension: Adding ELECTRIC_CAR, TRUCK doesn't break existing code
 * <p>
 * DESIGN PRINCIPLE: Encapsulation - vehicle types are now controlled constants
 * PATTERN: Type Safety Pattern
 * ----------------------------------------------------------------------------
 */
public enum VehicleType {
    MOTOR_CYCLE("Motor Cycle", 1.0),
    CAR("Car", 1.5),
    BUS("Bus", 3.0);

    private final String displayName;
    private final double sizeFactor;

    VehicleType(String displayName, double sizeFactor) {
        this.displayName = displayName;
        this.sizeFactor = sizeFactor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getSizeFactor() {
        return sizeFactor;
    }
}
