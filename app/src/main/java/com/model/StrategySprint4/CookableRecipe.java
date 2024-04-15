package com.model.StrategySprint4;

import com.model.Ingredient;
import com.model.User;
import com.viewmodels.UserViewModel;

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
            total += Integer.parseInt(ingredient.getCalories());
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
        this.calories = calculateCalories();
    }

    public void cook(UserViewModel userViewModel) {
        ArrayList<Ingredient> usedIngredients = this.ingredients;
        User user = userViewModel.getUser();
        ArrayList<Ingredient> pantry = user.getPantry();
        for (Ingredient recipeIngredient : usedIngredients) {
            // Check if the recipe ingredient exists in the user's pantry
            for (Ingredient pantryIngredient : pantry) {
                if (pantryIngredient.getName().equals(recipeIngredient.getName())) {
                    // Convert the quantity from String to int
                    int pantryQuantity = Integer.parseInt(pantryIngredient.getQuantity());
                    int recipeQuantity = Integer.parseInt(recipeIngredient.getQuantity());

                    // Decrement the quantity of the ingredient in the pantry
                    pantryQuantity -= recipeQuantity;

                    // If the quantity becomes zero, remove the ingredient from the pantry
                    if (pantryQuantity <= 0) {
                        pantry.remove(pantryIngredient);
                    } else {
                        // Update the quantity in the pantryIngredient object
                        pantryIngredient.setQuantity(String.valueOf(pantryQuantity));
                    }
                    break; // Exit the loop since the ingredient has been found and updated
                }
            }
        }
    }
}
