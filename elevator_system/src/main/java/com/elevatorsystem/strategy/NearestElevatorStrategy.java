package com.elevatorsystem.strategy;

import com.elevatorsystem.model.Direction;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.Request;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NearestElevatorStrategy implements ElevatorSelectorStrategy {
        @Override
        public Optional<Elevator> selectElevator(List<Elevator> elevators, Request request) {
            return elevators.stream()
                    .filter(e -> e.getDirection() == Direction.IDLE)  // only idle
                    .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - request.getTargetFloor())));
        }
}
