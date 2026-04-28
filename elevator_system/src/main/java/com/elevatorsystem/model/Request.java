package com.elevatorsystem.model;

public final class Request {
    private final Direction direction;
    private final RequestSource source;
    private final int targetFloor;

    public Request(RequestSource source, Direction direction, int targetFloor) {
        this.source = source;
        this.direction = direction;
        this.targetFloor = targetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestSource getSource() {
        return source;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
