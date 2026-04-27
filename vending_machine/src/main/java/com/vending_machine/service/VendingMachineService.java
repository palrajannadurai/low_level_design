package com.vending_machine.service;

import com.vending_machine.model.Product;
import com.vending_machine.model.Transaction;

public interface VendingMachineService {
    Transaction startTransaction();

    void insertMoney(Transaction tx, int amount);

    void selectProduct(Transaction tx, Product product);

    void cancelTransaction(Transaction tx);
}
