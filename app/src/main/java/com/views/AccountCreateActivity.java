package com.views;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.viewmodels.AccountCreateViewModel;

public class AccountCreateActivity extends AppCompatActivity {
    //Has textfields for username, new password, confirm password
    //Says messages like "Password must contain a number"
    //Connects to database
    //Null and whitespace checks for textfields
    //Moves to HomeActivity on successful account creation
    private EditText newUsername;
    private EditText newPassword;
    private Button createButton;
    private Button quitButton;
    private FirebaseAuth mAuth;
    private AccountCreateViewModel accCreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        accCreViewModel = AccountCreateViewModel.getInstance();
        newPassword = findViewById(R.id.editTextTextUsername);
        newUsername = findViewById(R.id.editTextTextUsername);
        createButton = findViewById(R.id.createAccountButton);
        quitButton = findViewById(R.id.quitButton);
        mAuth = FirebaseAuth.getInstance();

        quitButton.setOnClickListener(v -> {
            AccountCreateActivity.this.finish();
        });

        createButton.setOnClickListener(v -> {
            hideKeyboard();
            String username = newUsername.getText().equals("") ? "Null" : newUsername.getText().toString();
            String password = newPassword.getText().equals("") ? "Null" : newPassword.getText().toString();
            if (username.equals("Null") || password.equals("Null")) {
                Toast.makeText(this, "Username or password cannot be null",
                                    Toast.LENGTH_SHORT).show();
            } else if (accCreViewModel.createAccount(this, mAuth, username, password)) {
                Intent intent = new Intent(AccountCreateActivity.this, HomeActivity.class);
                startActivity(intent);
            }

        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
