package com.model;

public class Meal {
    private String name;
    private int calories;

    public Meal() {

    }

    public Meal(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public int getCalories() {
        return this.calories;
    }
}
