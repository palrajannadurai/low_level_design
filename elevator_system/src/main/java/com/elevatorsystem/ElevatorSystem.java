package com.elevatorsystem;

import com.elevatorsystem.model.Direction;
import com.elevatorsystem.model.Elevator;
import com.elevatorsystem.model.Request;
import com.elevatorsystem.model.RequestSource;
import com.elevatorsystem.observer.Display;
import com.elevatorsystem.strategy.ElevatorSelectorStrategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

public class ElevatorSystem {
    public static volatile ElevatorSystem instance;
    private final Map<Integer, Elevator> elevators;
    private final ElevatorSelectorStrategy strategy;
    private final BlockingQueue<Request> requestQueue;
    private final ExecutorService executorService;
    private volatile boolean running;

    public ElevatorSystem(int numOfElevators, ElevatorSelectorStrategy strategy) {
        this.elevators = new ConcurrentHashMap<>();
        for (int i = 1; i <= numOfElevators; i++) {
            Elevator e = new Elevator(i);
            e.addObserver(new Display());
            elevators.put(i, e);
        }
        this.strategy = strategy;
        this.requestQueue = new LinkedBlockingDeque<>();
        this.executorService = Executors.newFixedThreadPool(numOfElevators + 1);
        this.running = true;

        for (Elevator elevator: elevators.values()) {
            executorService.submit(elevator::run);
        }
        executorService.submit(this::dispatchLoop);
    }

    public static ElevatorSystem getInstance(int numOfElevators, ElevatorSelectorStrategy strategy) {
        if (instance == null) {
            synchronized (ElevatorSystem.class) {
                if (instance == null) {
                    instance = new ElevatorSystem(numOfElevators, strategy);
                }
            }
        }
        return instance;
    }

    public void requestElevator(int floor, Direction direction) {
        Request req = new Request(RequestSource.EXTERNAL, direction, floor);
        requestQueue.offer(req);
        System.out.printf("Request elevator at floor %d going %s%n", floor, direction);
    }

    // Internal request (select floor from inside an elevator)
    public void selectFloor(int elevatorId, int targetFloor) {
        Elevator e = elevators.get(elevatorId);
        if (e != null) {
            Direction dir = targetFloor > e.getCurrentFloor() ? Direction.UP : Direction.DOWN;
            Request req = new Request(RequestSource.INTERNAL, dir, targetFloor);
            e.addRequest(req);
        }
    }

    private void dispatchLoop() {
        while (running) {
            try {
                Request req = requestQueue.poll(1, TimeUnit.SECONDS);
                if (req == null) continue;
                Optional<Elevator> selected = strategy.selectElevator(
                        new ArrayList<>(elevators.values()), req);
                if (selected.isPresent()) {
                    selected.get().addRequest(req);
                } else {
                    // re-queue if no elevator available
                    requestQueue.offer(req);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void shutdown() {
        running = false;
        executorService.shutdownNow();
        for (Elevator e : elevators.values()) e.stopElevator();
    }

}
