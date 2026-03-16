package com.parkinglot.vehicles;

import com.parkinglot.enums.VehicleType;

/**
 * CLASS: Car
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS: Concrete implementation for standard cars
 * Could add car-specific features like sunroof, number of doors, etc.
 */
public class Car extends Vehicle {

    public Car(String licenseNumber, String color, String make) {
        super(licenseNumber, VehicleType.CAR, color, make);
    }

    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR, "Unknown", "Unknown");
    }
}
