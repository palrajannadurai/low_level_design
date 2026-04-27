package com.vending_machine.payment;

import com.vending_machine.inventory.Inventory;
import com.vending_machine.model.Denomination;

import java.util.*;

public class GreedyChangeStrategy implements ChangeStrategy {
    @Override
    public Map<Denomination, Integer> calculateChange(int amount, Inventory<Denomination> cashInventory) {

        if (amount == 0) return Collections.emptyMap();

        List<Denomination> denominations = Arrays.asList(Denomination.values());
        denominations.sort((a, b) -> b.getValue() - a.getValue());

        Map<Denomination, Integer> change = new HashMap<>();
        int remaining = amount;

        // Greedy try largest first:
        for (Denomination d : denominations) {
            int available = cashInventory.getQuantity(d);
            int needed = remaining / d.getValue();
            int take = Math.min(needed, available);

            if (take > 0) {
                change.put(d, take);
                remaining -= take * d.getValue();
            }

            if (remaining == 0) break;
        }
        // Return the change map only if we fully covered the amount, otherwise null (impossible)
        return remaining == 0 ? change : null;
    }
}
