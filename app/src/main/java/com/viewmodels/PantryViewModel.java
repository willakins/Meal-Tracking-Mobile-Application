package com.viewmodels;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.model.Ingredient;
import com.model.User;

import java.util.Objects;

public class PantryViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static PantryViewModel instance;
    private static DatabaseReference mDatabase;

    /**
     * Singleton Constructor
     * @return the userViewModel instance
     */
    public static synchronized PantryViewModel getInstance() {
        if (instance == null) {
            instance = new PantryViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
        }
        return instance;
    }

    /**
     *
     * @param context the currently displayed context for invalid input purposes
     * @param name EditText containing the ingredient name
     * @param quantity EditText containing ingredient quantity
     * @param calories EditText containing ingredient calories per quantity
     * @param expiration EditText containing days till expiration
     */
    public void addIngredient(Context context, String name, String quantity,
                              String calories, String expiration) {
        if (checkUserInput(context, name, quantity, calories, expiration)) {
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                        .child("Quantity").setValue(quantity);
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                    .child("Calories").setValue(calories);
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                    .child("Expiration").setValue(expiration);
            user.getPantry().add(new Ingredient(name, quantity, calories, expiration));
        }
    }

    /**
     * Helper function that checks user ingredient input.
     * If there are more than 1 invalid inputs, return the first one
     * down the list that we see
     *
     * @param context the current screen context
     * @param name the name of the ingredient
     * @param quantity the quantity of such ingredient
     * @param calories the calories per ingredient
     * @param expiration number of days till expiration
     * @return an int [0,4] where only 4 signifies valid input from the user
     */
    private boolean checkUserInput(Context context, String name, String quantity,
                                        String calories, String expiration) {
        int valid = 0;
        if (name.equals("") || !checkWhiteSpace(name)) {
            Toast.makeText(context, "Invalid name input",
                    Toast.LENGTH_SHORT).show();
        } else if (quantity.equals("") || !quantity.matches("\\d+")) {
            valid = 1;
            Toast.makeText(context, "Invalid quantity input",
                    Toast.LENGTH_SHORT).show();
        } else if (calories.equals("") || !calories.matches("\\d+")) {
            valid = 2;
            Toast.makeText(context, "Invalid calories input",
                    Toast.LENGTH_SHORT).show();
        } else if (!expiration.equals("") && !expiration.matches("\\d+")) {
            valid = 3;
            Toast.makeText(context, "Invalid expiration input",
                    Toast.LENGTH_SHORT).show();
        } else {
            valid = 4;
        }

        if (valid == 4) {
            Ingredient newIngredient = new Ingredient(name, quantity, calories, expiration);
            if (user.getPantry().contains(newIngredient)) {
                Toast.makeText(context, "Cannot add ingredients already in pantry",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper method for abstracting the process of checking a
     * string for whitespace
     *
     * @param input the string being checked for whitespace
     * @return false if the string contains whitespace; true otherwise
     */
    public boolean checkWhiteSpace(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isWhitespace(input.charAt(i))) {
                return false;
            } else if (Objects.equals(input.charAt(i), ' ')) {
                return false;
            }
        }
        return true;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
