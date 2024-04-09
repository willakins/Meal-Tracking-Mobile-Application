package com.viewmodels;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.model.Ingredient;
import com.model.Meal;
import com.model.ShoppingItem;
import com.model.User;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Singleton view model that abstracts the connection between the view, the database, and the model
 *
 * @author Will Akins
 */
public class UserViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static UserViewModel instance;
    private static DatabaseReference mDatabase;

    /**
     * Singleton Constructor
     * @return the userViewModel instance
     */
    public static synchronized UserViewModel getInstance() {
        if (instance == null) {
            instance = new UserViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
        }
        return instance;
    }

    /**
     * Abstracts the process of updating the user's data after the button click
     *
     * @param personalInfo the context so that invalid input message can be displayed
     * @param height the user's new height
     * @param weight the user's new weight
     * @param isMale true if the user is male; false otherwise
     */
    public void updateUserData(Context personalInfo, String height, String weight, boolean isMale) {
        if (checkUserInput(height, weight)) {
            user.setHeight(height);
            user.setWeight(weight);
            user.setIsMale(isMale);
            mDatabase.child("users").child(user.getUserId()).child("height").setValue(height);
            mDatabase.child("users").child(user.getUserId()).child("weight").setValue(weight);
            if (isMale) {
                mDatabase.child("users").child(user.getUserId()).child("isMale").setValue(true);
            } else {
                mDatabase.child("users").child(user.getUserId()).child("height").setValue(false);
            }
        } else {
            Toast.makeText(personalInfo, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Abstracts the process of adding a meal to the database and to the user instance
     *
     * @param name the name of the meal
     * @param calories the calories of the meal
     */
    public void addUserMeal(String name, String calories) {
        user.addCalories(Integer.parseInt(calories));
        user.getMeals().add(new Meal(name, Integer.parseInt(calories)));
        mDatabase.child("meals").child(user.getUserId()).setValue(user.getMeals());
        mDatabase.child("users").child(user.getUserId())
                    .child("caloriesToday").setValue(user.getCaloriesToday());
    }

    /**
     * Helper method that checks user text input for
     * null values or whitespace
     * @param height the first string to be checked
     * @param weight the second string to be checked
     * @return true if the input is valid, false otherwise
     */
    private boolean checkUserInput(String height, String weight) {
        //Checks if the input is empty
        //Log.d("The password is: " + password, password);
        if (height.equals("") || weight.equals("")) {
            return false;
        }
        //Each for loop checks one of the inputs for any whitespace
        for (int i = 0; i < height.length(); i++) {
            if (Character.isWhitespace(height.charAt(i))) {
                return false;
            } else if (Objects.equals(height.charAt(i), ' ')) {
                return false;
            }
        }
        for (int i = 0; i < weight.length(); i++) {
            if (Character.isWhitespace(weight.charAt(i))) {
                return false;
            } else if (Objects.equals(weight.charAt(i), ' ')) {
                return false;
            }
        }
        try {
            int h = Integer.parseInt(height);
            int w = Integer.parseInt(weight);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int addShoppingItem(String itemName, String quantity, String calories) {
        int validInput = checkShoppingInput(itemName, quantity, calories);
        if (validInput == 0) {
            ShoppingItem item = new ShoppingItem(itemName, quantity, calories);
            int index = user.findShoppingItem(item);
            if (index == -1) {
                user.getShoppingList().add(item);
            } else {
                ShoppingItem existingItem = user.getShoppingList().get(index);
                existingItem.setQuantity(Integer.toString(Integer.parseInt(existingItem
                        .getQuantity()) + Integer.parseInt(quantity)));
            }
            mDatabase.child("shoppingList").child(user.getUserId())
                    .child("Items").setValue(user.getShoppingList());
        }
        return validInput;
    }

    public static int checkShoppingInput(String itemName, String quantity, String calories) {
        int valid = 0;
        if (itemName.equals("") || !checkWhiteSpace(itemName)) {
            valid = 1;
        } else if (quantity.equals("") || !quantity.matches("\\d+")) {
            valid = 2;
        } else if (calories.equals("") || !calories.matches("\\d+")) {
            valid = 3;
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

    public void removeShoppingItem(ShoppingItem item) {
        mDatabase.child("shoppingList").child(user.getUserId()).child("Items")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String name = postSnapshot.child("name")
                                    .getValue(String.class);
                            String quantity = postSnapshot.child("quantity")
                                    .getValue(String.class);
                            String calories = postSnapshot.child("calories")
                                    .getValue(String.class);
                            if (item.getName().equals(name) && item.getQuantity().equals(quantity) && item.getCalories().equals(calories)) {
                                postSnapshot.getRef().removeValue();
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "assignUser:Failure");
                    }
                });
    }

    public void updateItems(ArrayList<ShoppingItem> items) {
        for (ShoppingItem item : items) {
            user.getShoppingList().remove(item);
            Ingredient wrapper = new Ingredient(item.getName(), item.getQuantity(), item.getCalories());
            int index = user.locateIngredient(wrapper);
            if (index != -1) {
                int newQuantity = Integer.parseInt(user.getPantry().get(index).getQuantity())
                        + Integer.parseInt(item.getQuantity());
                user.getPantry().get(index).setQuantity(Integer.toString(newQuantity));
            } else {
                user.getPantry().add(wrapper);
            }
            mDatabase.child("pantry").child(user.getUserId()).child(item.getName())
                    .child("Quantity").setValue(item.getQuantity());
        }
        mDatabase.child("shoppingList").child(user.getUserId())
                .child("Items").setValue(user.getShoppingList());
    }

    /**
     * Allows other classes to access the user
     *
     * @return the current logged in user
     */
    public User getUser() {
        return user;
    }

    public DatabaseReference getDatabase() {
        return this.mDatabase;
    }
}
