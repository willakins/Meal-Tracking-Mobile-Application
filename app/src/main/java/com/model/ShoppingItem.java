package com.model;
//Shopping list class
public class ShoppingItem {
    private String name;
    private String quantity;

    public ShoppingItem(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public String getQuantity() {
        return this.quantity;
    }
}
