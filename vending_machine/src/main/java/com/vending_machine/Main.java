package com.vending_machine;

import com.vending_machine.model.Denomination;
import com.vending_machine.model.Product;
import com.vending_machine.model.Transaction;
import com.vending_machine.service.VendingMachine;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        VendingMachine vm = VendingMachine.getInstance();

        // Setup products
        Product coke = new Product(100, "Coke", 25);
        Product chips = new Product(200, "Chips", 15);
        vm.restockProduct(coke, 2);
        vm.restockProduct(chips, 1);

        // Setup cash in machine
        vm.addCash(Denomination.COIN_10, 5);
        vm.addCash(Denomination.COIN_5, 5);
        vm.addCash(Denomination.NOTE_20, 2);

        // User 1 – buys coke with exact money
        Transaction t1 = vm.startTransaction();
        vm.insertMoney(t1, 20);
        vm.insertMoney(t1, 5);
        vm.selectProduct(t1, coke);

        // User 2 – buys chips with too much money (needs change)
        Transaction t2 = vm.startTransaction();
        vm.insertMoney(t2, 20);
        vm.selectProduct(t2, chips);  // should get $5 back

        // Concurrent test – two users try to buy the last coke
        Runnable buyCoke = () -> {
            VendingMachine v = VendingMachine.getInstance();
            Transaction tx = v.startTransaction();
            v.insertMoney(tx, 25);
            v.selectProduct(tx, coke);
        };
        Thread t3 = new Thread(buyCoke);
        Thread t4 = new Thread(buyCoke);
        t3.start();
        t4.start();
        t3.join();
        t4.join();  // only one succeeds

        // Admin collects cash
        vm.collectCash();
        vm.printInventory();
    }
}
