package com.vending_machine;

import java.util.Map;

interface AdminService {
    void restockProduct(Product product, int quantity);

    void addCash(Denomination d, int count);

    Map<Denomination, Integer> collectCash();

    void printInventory();
}