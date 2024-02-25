package com.views;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    //Has a quit button
    //Has header "Enter username and password to login"
    //Has 2 textFields for username and password
    //Has a login button that changes to HomeActivity
    //Has a button that changes to AccountCreateActivity
    //Null and whitespace checks for text fields
    //private EditText usernameEditText;
    private ImageView background;
    /**
    private EditText passwordEditText;
    private Button loginButton;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        background = findViewById(R.id.backgroundImage);

        /**
        usernameEditText = findViewById(R.id.usernameText);
        passwordEditText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        */
    }
}
