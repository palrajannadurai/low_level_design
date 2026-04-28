package com.elevatorsystem;

import com.elevatorsystem.model.Direction;
import com.elevatorsystem.strategy.NearestElevatorStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ElevatorSystem system = ElevatorSystem.getInstance(3, new NearestElevatorStrategy());
        system.requestElevator(3, Direction.UP);
        Thread.sleep(1000);
        system.requestElevator(5, Direction.DOWN);
        Thread.sleep(1000);
        system.selectFloor(1, 7);
        Thread.sleep(10000);
        system.shutdown();
    }
}