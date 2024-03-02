package com.viewmodels;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.model.Meal;
import com.model.User;
import com.views.InputMealActivity;

import java.util.Objects;


public class UserViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static UserViewModel instance;
    private static DatabaseReference mDatabase;

    public static synchronized UserViewModel getInstance() {
        if (instance == null) {
            instance = new UserViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
        }
        return instance;
    }

    public void setUserCalorieGoal(String calorieGoal) {

    }

    public void updateUserData(Context personalInfo, String height, String weight, boolean isMale) {
        if (checkUserInput(height, weight)) {
            user.setHeight(Integer.parseInt(height));
            user.setWeight(Integer.parseInt(weight));
            user.setIsMale(isMale);
            mDatabase.child("users").child(user.getUserId()).child("height").setValue(height);
            mDatabase.child("users").child(user.getUserId()).child("weight").setValue(weight);
            if (isMale) {
                mDatabase.child("users").child(user.getUserId()).child("height").setValue("Male");
            } else {
                mDatabase.child("users").child(user.getUserId()).child("height").setValue("Female");
            }
        } else {
            Toast.makeText(personalInfo, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void addUserMeal(String name, String calories) {
        user.addCalories(Integer.parseInt(calories));
        user.getMeals().add(new Meal(name, Integer.parseInt(calories)));
        mDatabase.child("meals").child(user.getUserId()).setValue(user.getMeals());
    }

    public User getUser() {
        return user;
    }

    /**
     * Helper method that checks user text input for
     * null values or whitespace
     * @param height the first input to be checked
     * @param weight the second input to be checked
     * @return true if the input is valid, false otherwise
     */
    private boolean checkUserInput(String height, String weight) {
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
        return true;
    }
}
