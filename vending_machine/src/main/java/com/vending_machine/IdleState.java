package com.vending_machine;

public class IdleState implements VendingState {
    @Override
    public void insertMoney(VendingMachine machine, Transaction tx, int amount) {
        tx.addMoney(amount);
        machine.setState(new HasMoneyState());
        System.out.printf("[Tx %s] Inserted $%d. Total: $%d%n", tx.getTransactionId(), amount, tx.getInsertedMoney());
    }

    @Override
    public void selectProduct(VendingMachine machine, Transaction tx, Product product) {
        System.out.println("Insert money first.");
    }

    @Override
    public void cancel(VendingMachine machine, Transaction tx) {
        System.out.println("Nothing to cancel.");
    }
}
