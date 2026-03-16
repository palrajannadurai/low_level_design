package com.parkinglot.vehicles;

import com.parkinglot.enums.VehicleType;

/**
 * CLASS: Motorcycle
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS: Motorcycles have special parking privileges
 * They can park in any spot type (most flexible)
 */
public class MotorCycle extends Vehicle {

    public MotorCycle(String licenseNumber, String color, String make) {
        super(licenseNumber, VehicleType.MOTOR_CYCLE, color, make);
    }

    public MotorCycle(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTOR_CYCLE, "Unknown", "Unknown");
    }

    /**
     * WHY OVERRIDE: Motorcycles are flexible - no special handling needed
     * But we could add motorcycle-specific logic here
     */
}
