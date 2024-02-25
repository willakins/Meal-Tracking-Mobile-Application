package com.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.viewmodels.LoginViewModel;

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
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        loginViewModel = LoginViewModel.getInstance();
        newUsername = findViewById(R.id.editTextUsername);
        newPassword = findViewById(R.id.editTextPassword);
        createButton = findViewById(R.id.createAccountButton);
        quitButton = findViewById(R.id.quitButton);
        mAuth = FirebaseAuth.getInstance();

        quitButton.setOnClickListener(v -> {
            AccountCreateActivity.this.finish();
        });

        createButton.setOnClickListener(v -> {
            String username = newUsername.getText().toString();
            String password = newPassword.getText().toString();
            hideKeyboard();
            if (!(username.equals("") || password.equals(""))) {
                if (loginViewModel.createAccount(this, mAuth, username, password)) {
                    Intent intent = new Intent(AccountCreateActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Invalid Input",
                        Toast.LENGTH_SHORT).show();
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
