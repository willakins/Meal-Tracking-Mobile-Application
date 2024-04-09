package com.model;
//Shopping list class
public class ShoppingItem {
    private String name;
    private String quantity;
    private String calories;

    public ShoppingItem(String name, String quantity, String calories) {
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getCalories() {
        return this.calories;
    }
}
