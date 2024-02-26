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
import com.views.AccountCreateActivity;
import com.views.InputMealActivity;
import com.views.LoginActivity;

import java.util.Objects;

public class LoginViewModel {
    private static LoginViewModel instance;
    private boolean success = false;

    public static synchronized LoginViewModel getInstance() {
        if (instance == null) {
            instance = new LoginViewModel();
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
                                FirebaseUser user = mAuth.getCurrentUser();
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
                                FirebaseUser user = mAuth.getCurrentUser();
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
}
