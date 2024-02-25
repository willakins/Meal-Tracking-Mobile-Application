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
    }
}
