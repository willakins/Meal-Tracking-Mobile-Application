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
import com.views.HomeActivity;
import com.views.LoginActivity;

public class LoginViewModel {
    private static LoginViewModel instance;
    private boolean success = false;

    public static synchronized LoginViewModel getInstance() {
        if (instance == null) {
            instance = new LoginViewModel();
        }
        return instance;
    }

    public boolean login(LoginActivity la, FirebaseAuth mAuth, String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(la, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            success = true;
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(la, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            success = false;
                        }
                    }
                });
        return success;
    }
}
