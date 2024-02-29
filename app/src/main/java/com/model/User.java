package com.model;

public class User {
    private String username;
    private String password;
    private int height;
    private int weight;
    private boolean isMale;
    //Height is measured in inches
    private static final int DEFAULT_HEIGHT = 64;
    //Weight is measured in pounds
    private static final int DEFAULT_WEIGHT = 130;
    //I support women
    private static final boolean DEFAULT_IS_MALE = false;

    /**
     * Constructor for no args case
     */
    public User() {
        this.height = DEFAULT_HEIGHT;
        this.weight = DEFAULT_WEIGHT;
        this.isMale = DEFAULT_IS_MALE;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean getIsMale() {
        return this.isMale;
    }

    /**
     * Helper method that calculates daily calorie goal for user
     * Mifflin-St Jeor Equation:
     * For men:     BMR = 10W + 6.25H - 5A + 5
     * For women:   BMR = 10W + 6.25H - 5A - 161
     * Assume age is always 20 since we arent asking user for that info
     *
     * @return an int representing the number of calories the user should eat
     * per day
     */
    public int calculateCalorieGoal() {
        double calories = 0.0;
        if (this.isMale) {
            calories = (10 * this.weight) + (6.25 * this.height) - 55;
        } else {
            calories = (10 * this.weight) + (6.25 * this.height) - 221;
        }
        return (int) Math.ceil(calories);
    }

}
