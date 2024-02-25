package com.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        newPassword = findViewById(R.id.editTextTextUsername);
        newUsername = findViewById(R.id.editTextTextUsername);
        createButton = findViewById(R.id.createAccountButton);
        quitButton = findViewById(R.id.quitButton);
    }
}
