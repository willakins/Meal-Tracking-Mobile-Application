package com.model;

import java.util.ArrayList;

//user class
public class User {
    private String username;
    private String password;
    private int height;
    private int weight;
    private boolean isMale;
    private int calorieGoal;
    private int caloriesToday;
    private String userId;
    private ArrayList<Meal> meals = new ArrayList<>();
    //Height is measured in inches
    private static final int DEFAULT_HEIGHT = 64;
    //Weight is measured in pounds
    private static final int DEFAULT_WEIGHT = 130;
    //I support women
    private static final boolean DEFAULT_IS_MALE = false;
    private static final int DEFAULT_CALORIE_GOAL = 2000;
    private static final int DEFAULT_CALORIES_TODAY = 0;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password) {
        this.height = DEFAULT_HEIGHT;
        this.weight = DEFAULT_WEIGHT;
        this.isMale = DEFAULT_IS_MALE;
        this.calorieGoal = DEFAULT_CALORIE_GOAL;
        this.caloriesToday = DEFAULT_CALORIES_TODAY;
        this.username = username;
        this.password = password;
        this.userId = generateUserId();
        this.meals = new ArrayList<Meal>();
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

    public void setCaloriesToday(int calories) {
        this.caloriesToday = calories;
    }

    public void addCalories(int calories) {
        this.caloriesToday += calories;
    }

    public void setCalorieGoal(int calories) {
        this.calorieGoal = calories;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
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

    public int getCaloriesToday() {
        return this.caloriesToday;
    }

    public int getCalorieGoal() {
        return this.calorieGoal;
    }

    public ArrayList<Meal> getMeals() {
        return this.meals;
    }

    public String getUserId() {
        return this.userId;
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
            calories = (10 * this.weight) + (6.25 * this.height) - 95;
        } else {
            calories = (10 * this.weight) + (6.25 * this.height) - 261;
        }
        this.calorieGoal = (int) Math.ceil(calories);
        return (int) Math.ceil(calories);
    }

    /**
     * Takes the first part of a user's email and generates a more human readable username
     *
     * @return a string representing the user
     */
    private String generateUserId() {
        int indexOfAtSymbol = this.username.indexOf("@");
        String justTheName = this.username.substring(0, indexOfAtSymbol);
        return justTheName;
    }

    public int getCaloricDeficit() {
        return this.calorieGoal - this.caloriesToday;
    }

}
