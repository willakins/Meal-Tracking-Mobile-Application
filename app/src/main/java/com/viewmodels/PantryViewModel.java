package com.viewmodels;

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
     * @param name EditText containing the ingredient name
     * @param quantity EditText containing ingredient quantity
     * @param calories EditText containing ingredient calories per quantity
     * @param expiration EditText containing days till expiration
     * @return an int that is 4 if the input was valid and 0 through 5 otherwise
     */
    public int addIngredient(String name, String quantity, String calories,
                             String expiration) {
        int validInput = checkUserInput(name, quantity, calories, expiration);
        if (expiration.equals("")) {
            expiration = "-1";
        }
        //4 means valid input but hasn't been checked for duplicates
        if (validInput == 4) {
            Ingredient newIngredient = new Ingredient(name, quantity, calories, expiration);
            if (user.findIngredient(newIngredient)) {
                return 5;
            }
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                            .child("Name").setValue(name);
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                    .child("Quantity").setValue(quantity);
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                    .child("Calories").setValue(calories);
            mDatabase.child("pantry").child(user.getUserId()).child(name)
                    .child("Expiration").setValue(expiration);
            user.getPantry().add(newIngredient);
        }
        return validInput;
    }

    /**
     * Helper function that checks user ingredient input.
     * If there are more than 1 invalid inputs, return the first one
     * down the list that we see
     *
     * @param name the name of the ingredient
     * @param quantity the quantity of such ingredient
     * @param calories the calories per ingredient
     * @param expiration number of days till expiration
     * @return an int [0,4] where only 4 signifies valid input from the user
     */
    public static int checkUserInput(String name, String quantity,
                                     String calories, String expiration) {
        int valid = 0;
        if (name.equals("") || !checkWhiteSpace(name)) {
            valid = 0;
        } else if (quantity.equals("") || !quantity.matches("\\d+")) {
            valid = 1;
        } else if (calories.equals("") || !calories.matches("\\d+")) {
            valid = 2;
        } else if (!expiration.equals("") && !expiration.matches("\\d+")) {
            valid = 3;
        } else {
            valid = 4;
        }
        return valid;
    }

    /**
     * Helper method for abstracting the process of checking a
     * string for whitespace
     *
     * @param input the string being checked for whitespace
     * @return false if the string contains whitespace; true otherwise
     */
    private static boolean checkWhiteSpace(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isWhitespace(input.charAt(i))) {
                return false;
            } else if (Objects.equals(input.charAt(i), ' ')) {
                return false;
            }
        }
        return true;
    }

    public static void removeIngredient(Ingredient ingredient) {
        mDatabase.child("pantry").child(user.getUserId())
                .child(ingredient.getName()).removeValue();
    }

    public static void setUser(User newUser) {
        user = newUser;
    }

    public static User getUser() {
        return user;
    }

    public static DatabaseReference getDatabase() {
        return mDatabase;
    }
}
