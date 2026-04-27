package com.vending_machine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Thread safe inventory
 * */
public class Inventory<T> {

    private final Map<T, Integer> stock = new ConcurrentHashMap<>();

    public void add(T item, int quantity) {
        stock.merge(item, quantity, Integer::sum);
    }

    public boolean has(T item, int quantity) {
        return stock.getOrDefault(item, 0) >= quantity;
    }

    public boolean deduct(T item, int quantity) {
        while (true) {
            Integer oldVal = stock.get(item);
            if (oldVal == null || oldVal < quantity) return false;
            Integer newVal = oldVal - quantity;
            if (stock.replace(item, oldVal, newVal)) return true;
            // retry if concurrent modification
        }
    }

    public int getQuantity(T item) {
        return stock.getOrDefault(item, 0);
    }

    public void deductAll(Map<T, Integer> items) {
        items.forEach(this::deduct); // if any fail it won't throw any exception. its design choice.
    }

    public Map<T, Integer> getAll() {
        return new HashMap<>(this.stock);
    }
}
