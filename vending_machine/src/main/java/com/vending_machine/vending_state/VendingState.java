package com.vending_machine.vending_state;

import com.vending_machine.service.VendingMachine;
import com.vending_machine.model.Product;
import com.vending_machine.model.Transaction;

public interface VendingState {
    void insertMoney(VendingMachine vendingMachine, Transaction txn, int money);

    void selectProduct(VendingMachine vendingMachine, Transaction txn, Product product);

    void cancel(VendingMachine vendingMachine, Transaction txn);
}
