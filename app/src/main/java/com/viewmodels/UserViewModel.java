package com.viewmodels;
import com.google.firebase.database.DatabaseReference;
import com.model.Meal;
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
        user.setHeight(Integer.parseInt(height));
        mDatabase.child("users").child(user.getUserId()).child("height").setValue(height);
    }

    public void setUserWeight(String weight) {
        user.setWeight(Integer.parseInt(weight));
        mDatabase.child("users").child(user.getUserId()).child("weight").setValue(weight);
    }

    public void setUserGender(boolean isMale) {
        user.setIsMale(isMale);
        if (isMale) {
            mDatabase.child("users").child(user.getUserId()).child("height").setValue("Male");
        } else {
            mDatabase.child("users").child(user.getUserId()).child("height").setValue("Female");
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
}
