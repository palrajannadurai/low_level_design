package com.elevatorsystem.state;

import com.elevatorsystem.model.Direction;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.Request;

public interface ElevatorState {
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
    void move(Elevator elevator);
}
