package com.viewmodels;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.model.Meal;
import com.model.User;
import com.views.AccountCreateActivity;
import com.views.InputMealActivity;
import com.views.LoginActivity;
import com.views.MealsFragment;

import java.util.ArrayList;
import java.util.Objects;

public class LoginViewModel {
    private static LoginViewModel instance;
    private static User user;
    private static FirebaseDatabase database;
    private static DatabaseReference mDatabase;
    private MealsFragment mealsFragment;

    public static synchronized LoginViewModel getInstance() {
        if (instance == null) {
            instance = new LoginViewModel();
            database = FirebaseDatabase.getInstance();
            mDatabase = database.getReference();
        }
        return instance;
    }

    /**
     * Allows a user to login through firebase
     * @param la the loginActivity instance
     * @param mAuth the firebase authentication
     * @param username the user's username/email
     * @param password the user's password
     */
    public void login(LoginActivity la, FirebaseAuth mAuth, String username, String password) {
        if (checkUserInput(username, password)) {
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(la, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                assignUser(username, password);
                                Intent intent = new Intent(la, InputMealActivity.class);
                                la.startActivity(intent);
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(la, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(la, "Invalid Input",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void createAccount(AccountCreateActivity aca, FirebaseAuth mAuth, String username, String password) {
        if (checkUserInput(username, password)) {
            mAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(aca, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                assignUser(username, password);
                                writeNewUser();
                                Intent intent = new Intent(aca, InputMealActivity.class);
                                aca.startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            }
                        }
                    });
        } else {
            Toast.makeText(aca, "Invalid Input",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Helper method that checks user text input for
     * null values or whitespace
     * @param username the first string to be checked
     * @param password the second string to be checked
     * @return true if the input is valid, false otherwise
     */
    private boolean checkUserInput(String username, String password) {
        //Checks if the input is empty
        //Log.d("The password is: " + password, password);
        if (username.equals("") || password.equals("")) {
            return false;
        }
        //Each for loop checks one of the inputs for any whitespace
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                return false;
            } else if (Objects.equals(username.charAt(i), ' ')) {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i))) {
                return false;
            } else if (Objects.equals(username.charAt(i), ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        if (this.user == null) {
            throw new NullPointerException("User was not initialized in getUser()");
        }
        return this.user;
    }

    /**
     *Creates a new user object and loads database values into it
     *
     * @param username
     * @param password
     */
    private void assignUser(String username, String password) {
        user = new User(username, password);
        this.initializeUserData();
    }

    /**
     *
     */
    public void writeNewUser() {
        mDatabase.child("users").child(user.getUserId()).setValue(user);
    }

    /**
     *
     * @return
     */
    public DatabaseReference getmDatabase() {
        return this.mDatabase;
    }

    private void initializeUserData() {
        //Loads previously inputted meals into user's arraylist
        mDatabase.child("meals").child(user.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                ArrayList<Meal> meals = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String mealName = postSnapshot.child("name").getValue(String.class);
                    String calories = String.valueOf(postSnapshot.child("calories").getValue(Long.class));
                    meals.add(new Meal(mealName, Integer.parseInt(calories)));
                }
                user.setMeals(meals);
                int calories = 0;
                for (Meal meal : user.getMeals()) {
                    calories += meal.getCalories();
                    Log.d(TAG, "Meal:" + meal.getCalories());
                }
                user.setCaloriesToday(calories);
                mealsFragment.updateUI();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "assignUser:Failure");
            }
        });

    }

    /**
     * Sets the current instance of the meals view so that we can dynamically
     * update the ui on data changes
     */
    public void setMealsFragment(MealsFragment mealsFragment) {
        this.mealsFragment = mealsFragment;
    }
}
