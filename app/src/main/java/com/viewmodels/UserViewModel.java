package com.viewmodels;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.model.User;

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

    public void setUserHeight(String height) {
        mDatabase.child("users").child(user.getUserId()).child("height").setValue(height);
    }

    public void setUserWeight(String weight) {

    }

    public void setUserGender(boolean isMale) {

    }

    public int getUserCaloriesToday() {
        return 0;
    }

}
