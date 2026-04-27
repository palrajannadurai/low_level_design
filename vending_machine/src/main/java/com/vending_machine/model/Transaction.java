package com.vending_machine.model;

public class Transaction {
    private final String transactionId;
    private Product selectedProduct;
    private int insertedMoney;

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    /* Money util methods */
    public int getInsertedMoney() {
        return insertedMoney;
    }

    public void addMoney(int amount) {
        this.insertedMoney += amount;
    }

    public void clearMoney() {
        this.insertedMoney = 0;
    }
}
