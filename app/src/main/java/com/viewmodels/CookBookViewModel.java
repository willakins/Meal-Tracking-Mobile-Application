package com.viewmodels;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.model.Ingredient;
import com.model.Recipe;
import com.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;



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

        if (checkUserInput(context, name, ingredients)) {
            ArrayList<Ingredient> parsedIngredients = parseInput(ingredients);
            //Updates cookbook database
            if (checkIngredientQuantities(context, parsedIngredients)) {
                mDatabase.child("cookbook").child(name).child("author")
                        .setValue(user.getUserId());
                for (Ingredient ingredient : parsedIngredients) {
                    mDatabase.child("cookbook").child(name).child(ingredient.getName())
                            .child("Quantity").setValue(ingredient.getQuantity());
                    mDatabase.child("cookbook").child(name).child(ingredient.getName())
                            .child("Calories").setValue(ingredient.getCalories());
                    mDatabase.child("cookbook").child(name).child(ingredient.getName())
                            .child("Expiration").setValue(ingredient.getExpiration());
                }
                user.getCookBook().add(new Recipe(name, parsedIngredients));
            }

        }

    }

    /**
     * Helper method for checking whether the user inputted
     * values correctly
     *
     * @param context the current screen context
     * @param parsedIngredients arrayList of ingredients
     * @return true if the input is valid; false otherwise
     */
    private boolean checkIngredientQuantities(Context context,
                                            ArrayList<Ingredient> parsedIngredients) {
        for (Ingredient ingredient : parsedIngredients) {
            if (Integer.parseInt(ingredient.getQuantity()) <= 0) {
                Toast.makeText(context, "Invalid quantity input",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
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
        if (name.equals("")) {
            Toast.makeText(context, "Invalid name input",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (ingredients.equals("")) {
            Toast.makeText(context, "Invalid ingredients input",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Helper method that converts a string of (ingredient, quantity) pairs
     * into an arraylist of Ingredient objects
     *
     * @param ingredients the EditText containing the user input
     * @return an array list of Ingredients
     */
    private ArrayList<Ingredient> parseInput(String ingredients) {
        ArrayList<Ingredient> parsedIngredients = new ArrayList<Ingredient>();
        StringTokenizer st = new StringTokenizer(ingredients, "(), ", false);
        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            if (!nextToken.equals(" ")) {
                String name = nextToken;
                String quantity = st.nextToken();
                parsedIngredients.add(new Ingredient(name, quantity, "100"));
            }
        }
        return parsedIngredients;
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

    public List<Recipe> getUserRecipes() {
        final List<Recipe> userRecipes = new ArrayList<>();
        mDatabase.child("cookbook").child(user.getUserId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Recipe recipe = snapshot.getValue(Recipe.class);
                            userRecipes.add(recipe);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
        return userRecipes;
    }
}
