package com.elevatorsystem.observer;

import com.elevatorsystem.model.Elevator;

public class Display implements ElevatorObserver {
    @Override
    public void update(Elevator elevator) {
        System.out.printf("Display: Elevator %d at floor %d, direction %s%n",
                elevator.getId(), elevator.getCurrentFloor(), elevator.getDirection());
    }
}
