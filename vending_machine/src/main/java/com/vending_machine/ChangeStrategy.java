package com.vending_machine;

import java.util.Map;

public interface ChangeStrategy {
    Map<Denomination, Integer> calculateChange(int amount, Inventory<Denomination> cashInventory);
}
