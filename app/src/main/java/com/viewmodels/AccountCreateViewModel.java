package com.viewmodels;

import static android.content.ContentValues.TAG;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.views.AccountCreateActivity;
import com.views.LoginActivity;

/** @noinspection unused*/
public class AccountCreateViewModel {
    private static AccountCreateViewModel instance;
    private boolean success = false;

    public static synchronized AccountCreateViewModel getInstance() {
        if (instance == null) {
            instance = new AccountCreateViewModel();
        }
        return instance;
    }

    public boolean createAccount(AccountCreateActivity aca, FirebaseAuth mAuth, String username, String password) {
        if (checkUserInput(username, password)) {
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(aca, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                success = true;
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(aca, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                success = false;
                            }
                        }
                    });
            return success;
        } else {
            Toast.makeText(aca, "Invalid Input",
                    Toast.LENGTH_SHORT).show();
            return false;
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
        if (username.equals("") || password.equals("")) {
            return false;
        }
        //Each for loop checks one of the inputs for any whitespace
        for (int i = 0; i < username.length(); i++) {
            if (Character.isWhitespace(username.charAt(i))) {
                return false;
            }
        }
        for (int i = 0; i < password.length(); i++) {
            if (Character.isWhitespace(password.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
