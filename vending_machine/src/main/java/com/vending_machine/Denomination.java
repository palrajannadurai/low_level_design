package com.vending_machine;

public enum Denomination {
    COIN_1(1), COIN_2(2), COIN_5(5), COIN_10(10),
    NOTE_20(20), NOTE_50(50), NOTE_100(100), NOTE_500(500);

    private final int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
