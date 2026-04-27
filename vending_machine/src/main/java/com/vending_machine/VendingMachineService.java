package com.vending_machine;

public interface VendingMachineService {
    Transaction startTransaction();

    void insertMoney(Transaction tx, int amount);

    void selectProduct(Transaction tx, Product product);

    void cancelTransaction(Transaction tx);
}
