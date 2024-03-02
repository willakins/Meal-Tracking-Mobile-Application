package com.viewmodels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.model.Meal;
import com.model.User;

import java.util.ArrayList;

public class UserViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static UserViewModel instance;
    private static DatabaseReference mDatabase;
    private static MealsFragment mealsView;

    public static synchronized UserViewModel getInstance() {
        if (instance == null) {
            instance = new UserViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
            mDatabase.addValueEventListener(calorieListener);
            mealsView = MealsFragment.getInstance();
        }
        return instance;
    }

    public void setUserCalorieGoal(String calorieGoal) {

    }

    public void setUserHeight(String height) {
        user.setHeight(Integer.parseInt(height));
        mDatabase.child("users").child(user.getUserId()).child("height").setValue(height);
    }

    public void setUserWeight(String weight) {

    }

    public void setUserGender(boolean isMale) {

    }

    public void addUserMeal(String name, String calories) {
        user.getMeals().add(new Meal(name, Integer.parseInt(calories)));
        mDatabase.child("meals").child(user.getUserId()).setValue(user.getMeals());
    }

    public int getUserCaloriesToday() {
        return 0;
    }

    public User getUser() {
        return user;
    }


    private static ValueEventListener calorieListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            ArrayList<Meal> meals = (ArrayList<Meal>) dataSnapshot.getValue();
            mealsView.updateCalorieUI(meals);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w(TAG, "loadCalories:canelled", databaseError.toException());
        }
    };
}
