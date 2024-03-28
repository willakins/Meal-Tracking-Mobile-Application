package com.model;
//Ingredient class
public class Ingredient {
    private String name;
    private String quantity;
    private String calories;
    private String expiration;
    private static final String defaultExpiration = "-1";

    public Ingredient(String name, String quantity, String calories,
                        String expiration) {
        this.name = name;
        this.quantity = quantity;
        this.calories = calories;
        this.expiration = expiration;
    }

    public Ingredient(String name, String quantity, String calories) {
        this(name, quantity, calories, defaultExpiration);
    }
}
