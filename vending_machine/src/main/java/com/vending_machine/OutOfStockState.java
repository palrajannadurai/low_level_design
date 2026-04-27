package com.vending_machine;

public class OutOfStockState implements VendingState {
    @Override
    public void insertMoney(VendingMachine machine, Transaction tx, int amount) {
        System.out.println("Machine out of stock. Returning money.");
        machine.refundTransaction(tx);
        machine.setState(new IdleState());
    }

    @Override
    public void selectProduct(VendingMachine machine, Transaction tx, Product product) {
        System.out.println("Out of stock. Please try later.");
    }

    @Override
    public void cancel(VendingMachine machine, Transaction tx) {
        machine.refundTransaction(tx);
        machine.setState(new IdleState());
    }
}
