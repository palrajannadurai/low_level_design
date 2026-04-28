package com.elevatorsystem.state;

import com.elevatorsystem.model.Direction;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.Request;

public class IdleState implements ElevatorState {
    @Override
    public void addRequest(Elevator elevator, Request request) {
        if (request.getTargetFloor() > elevator.getCurrentFloor()) {
            elevator.setElevatorState(new MovingUpState());
        } else if (request.getTargetFloor() < elevator.getCurrentFloor()) {
            elevator.setElevatorState(new MovingDownState());
        }
        elevator.addRequestToAppropriateSet(request);
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }

    @Override
    public void move(Elevator elevator) { /* do nothing, wait for requests */ }
}
