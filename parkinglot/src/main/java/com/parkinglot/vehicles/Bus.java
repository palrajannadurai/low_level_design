package com.parkinglot.vehicles;

import com.parkinglot.enums.VehicleType;

/**
 * CLASS: Bus
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS: Buses have strict size requirements
 * Can only park in LARGE spots
 */
public class Bus extends Vehicle {

    private int passengerCapacity;

    public Bus(String licenseNumber, String color, String make, int passengerCapacity) {
        super(licenseNumber, VehicleType.BUS, color, make);
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public boolean needSpecialHandling() {
        return true;
    }
}
