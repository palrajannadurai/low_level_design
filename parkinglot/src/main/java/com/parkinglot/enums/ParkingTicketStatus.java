package com.parkinglot.enums;

/**
 * ENUM: ParkingTicketStatus
 * ----------------------------------------------------------------------------
 * WHY THIS EXISTS:
 * 1. Lifecycle Management: Track ticket through its lifecycle
 * 2. Fraud Prevention: Can't use same ticket twice
 * 3. Reporting: Know how many cars currently parked vs completed
 *
 * DESIGN PRINCIPLE: State Pattern - ticket has well-defined states
 * ----------------------------------------------------------------------------
 */
public enum ParkingTicketStatus {
    ACTIVE,
    PAID,
    LOST,
    VOID;
}