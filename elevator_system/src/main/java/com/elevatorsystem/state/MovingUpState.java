package com.elevatorsystem.state;

import com.elevatorsystem.model.Direction;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.Request;

public class MovingUpState implements ElevatorState {
    @Override
    public void addRequest(Elevator elevator, Request request) {
        elevator.addRequestToAppropriateSet(request);
    }
    @Override
    public Direction getDirection() { return Direction.UP; }
    @Override
    public void move(Elevator elevator) {
        int current = elevator.getCurrentFloor();
        int next = current + 1;
        elevator.setCurrentFloor(next);
        elevator.checkAndServeRequestsAtFloor(next);
        if (elevator.getUpRequests().isEmpty() && elevator.getDownRequests().isEmpty()) {
            elevator.setElevatorState(new IdleState());
        } else if (elevator.getUpRequests().isEmpty()) {
            elevator.setElevatorState(new MovingDownState());
        }
    }
}
