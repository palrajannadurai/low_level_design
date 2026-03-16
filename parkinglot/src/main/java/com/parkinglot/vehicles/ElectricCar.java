package com.parkinglot.vehicles;

import com.parkinglot.enums.VehicleType;

/**
 * CLASS: ElectricCar
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS: New vehicle type with special requirements
 * Added without changing any existing code - Open/Closed Principle
 */
public class ElectricCar extends Vehicle {

    private double batteryLevel;

    public ElectricCar(String licenseNumber, String color, String make, double batteryLevel) {
        super(licenseNumber, VehicleType.ELECTRIC_CAR, color, make);
        this.batteryLevel = batteryLevel;
    }

    public boolean needsCharging() {
        return this.batteryLevel < 20.0;
    }

    @Override
    public boolean needSpecialHandling() {
        return needsCharging();
    }
}
