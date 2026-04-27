package com.vending_machine.payment;

import com.vending_machine.inventory.Inventory;
import com.vending_machine.model.Denomination;

import java.util.Map;

public interface ChangeStrategy {
    Map<Denomination, Integer> calculateChange(int amount, Inventory<Denomination> cashInventory);
}
