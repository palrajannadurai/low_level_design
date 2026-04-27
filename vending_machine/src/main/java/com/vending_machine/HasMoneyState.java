package com.vending_machine;

public class HasMoneyState implements VendingState {
    @Override
    public void insertMoney(VendingMachine machine, Transaction tx, int amount) {
        tx.addMoney(amount);
        System.out.printf("[Tx %s] Inserted $%d. Total: $%d%n",
                tx.getTransactionId(), amount, tx.getInsertedMoney());
    }

    @Override
    public void selectProduct(VendingMachine machine, Transaction tx, Product product) {
        tx.setSelectedProduct(product);
        machine.dispenseProduct(tx);
    }

    @Override
    public void cancel(VendingMachine machine, Transaction tx) {
        int refund = tx.getInsertedMoney();
        tx.clearMoney();
        machine.setState(new IdleState());
        System.out.printf("[Tx %s] Cancelled. Refund $%d%n", tx.getTransactionId(), refund);
    }
}
