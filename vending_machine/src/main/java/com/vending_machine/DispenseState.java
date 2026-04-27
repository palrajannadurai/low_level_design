package com.vending_machine;

public class DispenseState implements VendingState{
    @Override
    public void insertMoney(VendingMachine machine, Transaction tx, int amount) {
        System.out.println("Please wait, dispensing...");
    }
    @Override
    public void selectProduct(VendingMachine machine, Transaction tx, Product product) {
        System.out.println("Already dispensing, please wait.");
    }
    @Override
    public void cancel(VendingMachine machine, Transaction tx) {
        System.out.println("Cannot cancel, product dispensing.");
    }
}
