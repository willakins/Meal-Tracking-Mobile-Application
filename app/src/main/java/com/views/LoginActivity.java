package com.views;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    //Has a quit button
    //Has header "Enter username and password to login"
    //Has 2 textFields for username and password
    //Has a login button that changes to HomeActivity
    //Has a button that changes to AccountCreateActivity
    //Null and whitespace checks for text fields
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button toCreateAccount;
    private Button quitButton;
    private FirebaseAuth mAuth;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = LoginViewModel.getInstance();
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        toCreateAccount = findViewById(R.id.createAccountButton);
        quitButton = findViewById(R.id.quitButton);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(v -> {
            hideKeyboard();
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            loginViewModel.login(this, mAuth, username, password);
        });

        toCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, AccountCreateActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        });

        quitButton.setOnClickListener(v -> {
            LoginActivity.this.finish();
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