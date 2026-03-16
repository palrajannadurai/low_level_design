package com.parkinglot.enums;


/**
 * ENUM: ParkingSpotType
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS:
 * 1. Categorization: Different spots have different purposes and pricing
 * 2. Business Rules: Handicapped spots have special rules
 * 3. Reporting: Track utilization by spot type
 * <p>
 * DESIGN PRINCIPLE: Separation of Concerns - spot types independent of vehicles
 * ----------------------------------------------------------------------------
 */
public enum ParkingSpotType {
    MOTOR_CYCLE("Motorcycle Spot", 1.0, false, false),
    COMPACT("Compact", 2.0, false, false),
    LARGE("Large Spot", 3.0, false, false),
    HANDICAPPED("Handicapped Spot", 2.0, true, false),
    ELECTRIC("Electric Spot", 3.0, false, true),
    VIP("Vip Spot", 5.0, false, false);


    private final String displayName;
    private final double hourlyRate;
    private final boolean isHandicapped;
    private final boolean hasCharging;

    ParkingSpotType(String displayName, double hourlyRate, boolean isHandicapped, boolean hasCharging) {
        this.displayName = displayName;
        this.hourlyRate = hourlyRate;
        this.isHandicapped = isHandicapped;
        this.hasCharging = hasCharging;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public boolean isHandicapped() {
        return isHandicapped;
    }

    public boolean isHasCharging() {
        return hasCharging;
    }
}