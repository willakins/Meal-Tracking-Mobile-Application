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
import com.model.Ingredient;
import com.model.Meal;
import com.model.Recipe;
import com.model.User;
import com.views.AccountCreateActivity;
import com.views.HomeActivity;
import com.views.LoginActivity;
import com.views.MealsFragment;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Singleton view model that abstracts the connection between the view,
 * the user database, and the model
 *
 * @author Will Akins
 */
public class LoginViewModel {
    private static LoginViewModel instance;
    private static User user;
    private static FirebaseDatabase database;
    private static DatabaseReference mDatabase;
    private MealsFragment mealsFragment;

    /**
     * Singleton Constructor
     * @return the instance of the viewModel
     */
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
     *
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
                                Intent intent = new Intent(la, HomeActivity.class);
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

    /**
     * Allows a user to create a new account and stores it in user database
     *
     * @param aca the AccountCreateActivity context so that Invalid Input can be displayed
     * @param mAuth the firebase authorization
     * @param username the user's new username
     * @param password the user's new password
     */
    public void createAccount(AccountCreateActivity aca, FirebaseAuth mAuth,
                              String username, String password) {
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
                                Intent intent = new Intent(aca, HomeActivity.class);
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
     * Creates a new user node into the user database
     */
    public void writeNewUser() {
        mDatabase.child("users").child(user.getUserId()).setValue(user);
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
            } else if (Objects.equals(password.charAt(i), ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     *Creates a new user object and loads database values into it
     *
     * @param username the user's username
     * @param password the user's password
     */
    private void assignUser(String username, String password) {
        user = new User(username, password);
        /**
         * TODO 2: load previously inputted meals into the user's arraylist
         */
        //Loads previously inputted meals into user's arraylist
        mDatabase.child("meals").child(user.getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        ArrayList<Meal> meals = new ArrayList<>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String mealName = postSnapshot.child("name").getValue(String.class);
                            String calories = String.valueOf(postSnapshot.child("calories")
                                                .getValue(Long.class));
                            meals.add(new Meal(mealName, Integer.parseInt(calories)));
                        }
                        user.setMeals(meals);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "assignUser:Failure");
                    }
                });
        mDatabase.child("users").child(user.getUserId())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(final DataSnapshot dataSnapshot) {
                            user.setCalorieGoal(dataSnapshot.child("calorieGoal")
                                    .getValue(Long.class).intValue());
                            user.setCaloriesToday(dataSnapshot.child("caloriesToday")
                                    .getValue(Long.class).intValue());
                            user.setIsMale(dataSnapshot.child("isMale").getValue(Boolean.class));
                            user.setHeight(dataSnapshot.child("height").getValue(String.class));
                            user.setWeight(dataSnapshot.child("weight").getValue(String.class));
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "assignUser:Failure");
                        }
                    });
        mDatabase.child("cookbook")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        ArrayList<Recipe> cookbook = new ArrayList<>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String recipeName = postSnapshot.child("Name").getValue(String.class);
                            ArrayList<Ingredient> ingredients = new ArrayList<>();
                            for (DataSnapshot ds : postSnapshot.child("Ingredients").getChildren()) {
                                String name = ds.child("Name").getValue(String.class);
                                String quantity = ds.child("Quantity").getValue(String.class);
                                String calories = ds.child("Calories").getValue(String.class);
                                String expiration = ds.child("Expiration")
                                        .getValue(String.class);
                                ingredients.add(new Ingredient(name, quantity, calories,
                                        expiration));
                            }
                            cookbook.add(new Recipe(recipeName, ingredients));
                        }
                        user.setCookbook(cookbook);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(TAG, "assignUser:Failure");
                    }
                });
        mDatabase.child("pantry").child(user.getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        ArrayList<Ingredient> pantry = new ArrayList<>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String name = postSnapshot.child("Name")
                                    .getValue(String.class);
                            String quantity = postSnapshot.child("Quantity")
                                    .getValue(String.class);
                            String calories = postSnapshot.child("Calories")
                                    .getValue(String.class);
                            String expiration = postSnapshot.child("Expiration")
                                    .getValue(String.class);
                            pantry.add(new Ingredient(name, quantity, calories, expiration));
                        }
                        user.setPantry(pantry);
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
     *
     * @param mealsFragment the Context used for toast messages
     */
    public void setMealsFragment(MealsFragment mealsFragment) {
        this.mealsFragment = mealsFragment;
    }

    /**
     * Allows other classes to access the database
     * @return a reference to the database containing both meals and users
     */
    public DatabaseReference getmDatabase() {
        return this.mDatabase;
    }

    /**
     * Allows userViewModel to get an instance of the user
     * Only userViewModel should use this method; other classes should access the user
     * through the userViewModel.
     *
     * @return an instance of the current user
     */
    public User getUser() {
        return this.user;
    }
}
