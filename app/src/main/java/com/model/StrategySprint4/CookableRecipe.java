package com.model.StrategySprint4;

import com.model.Ingredient;
import com.model.StrategySprint4.Recipe;

import java.util.ArrayList;

public class CookableRecipe implements Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private int calories;

    public CookableRecipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calculateCalories();
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public int calculateCalories() {
        int total = 0;
        for (Ingredient ingredient : this.ingredients) {
            if (ingredient != null && ingredient.getCalories() != null) {
                total += Integer.parseInt(ingredient.getCalories());
            }

        }
        return total;
    }

    public int getCalories() {
        return this.calories;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void cook() {

    }
}
