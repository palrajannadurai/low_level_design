package com.elevatorsystem.model;

import com.elevatorsystem.observer.ElevatorObserver;
import com.elevatorsystem.state.ElevatorState;
import com.elevatorsystem.state.IdleState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    private final int id;
    private final AtomicInteger currentFloor;
    private final Set<Integer> upRequests;
    private final Set<Integer> downRequests;
    private final List<ElevatorObserver> observers;
    private volatile ElevatorState state;
    private volatile boolean running;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = new AtomicInteger(0);
        this.upRequests = new ConcurrentSkipListSet<>();
        this.downRequests = new ConcurrentSkipListSet<>(Collections.reverseOrder());
        this.observers = new ArrayList<>();
        this.state = new IdleState();
        this.running = true;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public void setCurrentFloor(int floor) {
        currentFloor.set(floor);
        notifyObservers();
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public Set<Integer> getUpRequests() {
        return upRequests;
    }

    public Set<Integer> getDownRequests() {
        return downRequests;
    }

    public void setElevatorState(ElevatorState newState) {
        this.state = newState;
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void stopElevator() {
        running = false;
    }

    public void addRequest(Request request) {
        state.addRequest(this, request);
    }

    public void addRequestToAppropriateSet(Request request) {
        int floor = request.getTargetFloor();
        if (request.getSource() == RequestSource.INTERNAL) {
            // internal request: destination floor
            if (floor > currentFloor.get()) upRequests.add(floor);
            else if (floor < currentFloor.get()) downRequests.add(floor);
        } else {
            if (floor > currentFloor.get()) upRequests.add(floor);
            else if (floor < currentFloor.get()) downRequests.add(floor);
        }
    }

    public void checkAndServeRequestsAtFloor(int floor) {
        boolean served = false;
        if (upRequests.remove(floor)) served = true;
        if (downRequests.remove(floor)) served = true;
        if (served) {
            System.out.printf("Elevator %d serving floor %d%n", id, floor);
            notifyObservers(); // Simulate door open/close
        }
    }

    private void notifyObservers() {
        for (ElevatorObserver obs : observers) {
            obs.update(this);
        }
    }

    public void move() {
        state.move(this);
    }

    public void run() {
        while (running) {
            move();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
