package com.model;

import java.util.ArrayList;

//user class
public class User {
    private String username;
    private String password;
    private String height;
    private String weight;
    private boolean isMale;
    private int calorieGoal;
    private int caloriesToday;
    private String userId;
    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<Recipe> cookBook = new ArrayList<>();
    private ArrayList<Ingredient> pantry = new ArrayList<>();
    private ArrayList<ShoppingItem> shoppingList = new ArrayList<>();


    //Height is measured in inches
    private static final String DEFAULT_HEIGHT = "64";
    //Weight is measured in pounds
    private static final String DEFAULT_WEIGHT = "130";
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
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
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

    public void setCookbook(ArrayList<Recipe> cookbook) {

        this.cookBook = cookbook;
    }

    public void setPantry(ArrayList<Ingredient> pantry) {
        this.pantry = pantry;
    }

    public void setShoppingList(ArrayList<ShoppingItem> newList) {
        this.shoppingList = newList;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHeight() {
        return this.height;
    }

    public String getWeight() {
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

    public ArrayList<Recipe> getCookBook() {
        return this.cookBook;
    }

    public ArrayList<Ingredient> getPantry() {
        return this.pantry;
    }

    public ArrayList<ShoppingItem> getShoppingList() {
        return this.shoppingList;
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
            calories = (10 * Integer.parseInt(this.weight))
                            + (6.25 * Integer.parseInt(this.height)) - 95;
        } else {
            calories = (10 * Integer.parseInt(this.weight))
                            + (6.25 * Integer.parseInt(this.height)) - 261;

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

    /**
     * helper method for determining if a user's pantry contains an ingredient
     *
     * @param target the ingredient that the pantry is being searched for
     * @return true if the ingredient is in the pantry; false otherwise
     */
    public boolean findIngredient(Ingredient target) {
        for (Ingredient ingredient : this.pantry) {
            if (target.getName() != null && ingredient.getName()
                    .toUpperCase().equals(target.getName().toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method for finding the index of an ingredient in the pantry
     *
     * @param target The ingredient being searched for
     * @return the index of the ingredient; -1 if not found
     */
    public int locateIngredient(Ingredient target) {
        for (int i = 0; i < this.pantry.size(); i++) {
            if (target.getName() != null && pantry.get(i).getName().toUpperCase()
                    .equals(target.getName().toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method for locating duplicates in shopping list
     *
     * @param target the shopping Item being searched for
     * @return -1 if not found, an int representing the index found at otherwise
     */
    public int findShoppingItem(ShoppingItem target) {
        for (int i = 0; i < this.shoppingList.size(); i++) {
            if (target.getName() != null && shoppingList.get(i).getName()
                    .toUpperCase().equals(target.getName().toUpperCase())) {
                return i;
            }
        }
        return -1;
    }
}
