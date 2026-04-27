package com.vending_machine.service;

import com.vending_machine.inventory.Inventory;
import com.vending_machine.model.Denomination;
import com.vending_machine.model.Product;
import com.vending_machine.model.Transaction;
import com.vending_machine.payment.ChangeStrategy;
import com.vending_machine.payment.GreedyChangeStrategy;
import com.vending_machine.vending_state.DispenseState;
import com.vending_machine.vending_state.IdleState;
import com.vending_machine.vending_state.OutOfStockState;
import com.vending_machine.vending_state.VendingState;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class VendingMachine implements VendingMachineService, AdminService {
    private static volatile VendingMachine instance;
    private final Inventory<Product> productInventory = new Inventory<>();
    private final Inventory<Denomination> cashInventory = new Inventory<>();
    private final ChangeStrategy changeStrategy = new GreedyChangeStrategy();
    private final ReentrantLock globalLock = new ReentrantLock();
    private final AtomicLong transactionIdGenerator = new AtomicLong(1);

    private volatile VendingState state;

    private VendingMachine() {
        this.state = new IdleState();
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    // ==================== User actions (VendingMachineService) ====================
    @Override
    public Transaction startTransaction() {
        return new Transaction("TX" + transactionIdGenerator.getAndIncrement());
    }

    @Override
    public void insertMoney(Transaction tx, int amount) {
        globalLock.lock();
        try {
            state.insertMoney(this, tx, amount);
        } finally {
            globalLock.unlock();
        }
    }

    @Override
    public void selectProduct(Transaction tx, Product product) {
        globalLock.lock();
        try {
            state.selectProduct(this, tx, product);
        } finally {
            globalLock.unlock();
        }
    }

    @Override
    public void cancelTransaction(Transaction tx) {
        globalLock.lock();
        try {
            state.cancel(this, tx);
        } finally {
            globalLock.unlock();
        }
    }

    // ==================== Internal core logic (under globalLock) ====================
    public void dispenseProduct(Transaction tx) {
        // already locked
        Product product = tx.getSelectedProduct();
        if (product == null) {
            System.out.println("No product selected.");
            return;
        }

        // Check stock
        if (!productInventory.has(product, 1)) {
            System.out.printf("[Tx %s] Product out of stock.%n", tx.getTransactionId());
            refundTransaction(tx);
            state = new OutOfStockState();
            state = new IdleState(); // after notifying user, go idle
            return;
        }

        int paid = tx.getInsertedMoney();
        int price = product.getPrice();
        if (paid < price) {
            System.out.printf("[Tx %s] Insufficient money. Need $%d more.%n",
                    tx.getTransactionId(), price - paid);
            return;
        }

        int changeAmount = paid - price;
        Map<Denomination, Integer> change = changeStrategy.calculateChange(changeAmount, cashInventory);
        if (change == null && changeAmount > 0) {
            System.out.printf("[Tx %s] Cannot return exact change. Cancelling.%n", tx.getTransactionId());
            refundTransaction(tx);
            state = new IdleState();
            return;
        }

        // All conditions satisfied – perform transaction atomically
        state = new DispenseState();

        boolean stockDeducted = productInventory.deduct(product, 1);
        if (!stockDeducted) { // safety check
            System.out.printf("[Tx %s] Stock changed concurrently. Refunding.%n", tx.getTransactionId());
            refundTransaction(tx);
            state = new IdleState();
            return;
        }

        if (changeAmount > 0) {
            cashInventory.deductAll(change);
            System.out.printf("[Tx %s] Change returned: %s%n", tx.getTransactionId(), change);
        }
        System.out.printf("[Tx %s] Dispensing: %s%n", tx.getTransactionId(), product.getName());
        tx.clearMoney();
        state = new IdleState();
    }

    public void refundTransaction(Transaction tx) {
        int refund = tx.getInsertedMoney();
        tx.clearMoney();
        System.out.printf("[Tx %s] Refund $%d%n", tx.getTransactionId(), refund);
    }

    public void setState(VendingState newState) {
        this.state = newState;
    }

    // ==================== Admin actions ====================
    @Override
    public void restockProduct(Product product, int quantity) {
        globalLock.lock();
        try {
            productInventory.add(product, quantity);
            System.out.printf("Admin: Restocked %s x%d%n", product.getName(), quantity);
        } finally {
            globalLock.unlock();
        }
    }

    @Override
    public void addCash(Denomination denom, int count) {
        globalLock.lock();
        try {
            cashInventory.add(denom, count);
            System.out.printf("Admin: Added %d x %s%n", count, denom);
        } finally {
            globalLock.unlock();
        }
    }

    @Override
    public Map<Denomination, Integer> collectCash() {
        globalLock.lock();
        try {
            Map<Denomination, Integer> allCash = cashInventory.getAll();
            for (Denomination d : Denomination.values()) {
                cashInventory.deduct(d, cashInventory.getQuantity(d));
            }
            System.out.println("Admin: Collected cash: " + allCash);
            return allCash;
        } finally {
            globalLock.unlock();
        }
    }

    @Override
    public void printInventory() {
        globalLock.lock();
        try {
            System.out.println("Products: " + productInventory.getAll());
            System.out.println("Cash: " + cashInventory.getAll());
        } finally {
            globalLock.unlock();
        }
    }
}
