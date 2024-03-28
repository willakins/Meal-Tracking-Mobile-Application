package com.viewmodels;

import android.content.Context;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.model.Ingredient;
import com.model.Recipe;
import com.model.User;

import java.util.ArrayList;

public class CookBookViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static CookBookViewModel instance;
    private static DatabaseReference mDatabase;

    /**
     * Singleton Constructor
     * @return the userViewModel instance
     */
    public static synchronized CookBookViewModel getInstance() {
        if (instance == null) {
            instance = new CookBookViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
        }
        return instance;
    }

    /**
     * TODO 2: Implement below methods; database connection has been done already
     * This first method has mostly been completed
     * Maybe need to consider how the scrollable list is updated at the end of this function call.
     * Check the user input for things like negative quantities.
     * Don't forget to update LoginViewModel's assignUser function
     * so that recipes persist between logins.
     *
     * @param context the current screen context for invalid input usage
     * @param editTextName the name of the recipe still in EditText format
     * @param editTextIngredients the list of ingredients still in EditText format
     */
    public void addRecipe(Context context, EditText editTextName,
                          EditText editTextIngredients) {
        String name = editTextName.getText().toString();
        String ingredients = editTextIngredients.getText().toString();
        ArrayList<Ingredient> parsedIngredients = parseInput(editTextIngredients);
        if (checkUserInput(context, name, ingredients)) {
            //Updates cookbook database
            mDatabase.child("cookbook").child(name).child("author")
                    .setValue(user.getUserId());
            for (Ingredient ingredient: parsedIngredients) {
                mDatabase.child("cookbook").child(name).child(ingredient.getName())
                        .child("Quantity").setValue(ingredient.getQuantity());
                mDatabase.child("cookbook").child(name).child(ingredient.getName())
                        .child("Calories").setValue(ingredient.getCalories());
                mDatabase.child("cookbook").child(name).child(ingredient.getName())
                        .child("Expiration").setValue(ingredient.getExpiration());
            }
        }
        user.getCookBook().add(new Recipe(name, parsedIngredients));
    }

    /**
     * Helper method for checking whether the user inputted
     * values correctly
     *
     * @param context the current screen context
     * @param name the name of the recipe
     * @param ingredients the string of (ingredient, quantity) pairs
     * @return true if the input is valid; false otherwise
     */
    private boolean checkUserInput(Context context, String name, String ingredients) {
        return false;
    }

    /**
     * Helper method that converts a string of (ingredient, quantity) pairs
     * into an arraylist of Ingredient objects
     *
     * @param ingredients the EditText containing the user input
     * @return an array list of Ingredients
     */
    private ArrayList<Ingredient> parseInput(EditText ingredients) {
        return new ArrayList<Ingredient>();
    }

}
