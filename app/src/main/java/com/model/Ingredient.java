package com.model;
//Ingredient class
public class Ingredient {
    private String name;
    private String quantity;
    private static final String DEFAULT_QUANTITY = "1";
    private String calories;
    private static final String DEFAULT_CALORIES = "100";
    private String expiration;
    private static final String DEFAULT_EXPIRATION = "-1";

    public Ingredient(String name, String quantity, String calories,
                        String expiration) {
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
        this.expiration = expiration;
    }

    public Ingredient(String name, String quantity, String calories) {
        this(name, quantity, calories, DEFAULT_EXPIRATION);
    }

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
        this.calories = DEFAULT_CALORIES;
        this.expiration = DEFAULT_EXPIRATION;
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

    public String getExpiration() {
        return this.expiration;
    }
}
