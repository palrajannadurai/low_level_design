package com.vending_machine.service;

import com.vending_machine.model.Denomination;
import com.vending_machine.model.Product;

import java.util.Map;


/*
* 1. Restock the product
* 2. Add cash to the machine
* 3. Collect cash from the machine
* */
interface AdminService {
    void restockProduct(Product product, int quantity);
    void addCash(Denomination d, int count);
    Map<Denomination, Integer> collectCash();
    void printInventory();
}