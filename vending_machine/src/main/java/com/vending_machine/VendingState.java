package com.vending_machine;

public interface VendingState {
    void insertMoney(VendingMachine vendingMachine, Transaction txn, int money);
    void selectProduct(VendingMachine vendingMachine, Transaction txn, Product product);
    void cancel(VendingMachine vendingMachine, Transaction txn);
}
