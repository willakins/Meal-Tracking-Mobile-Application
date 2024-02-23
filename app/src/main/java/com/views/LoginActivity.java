package com.views;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    //Has a quit button
    //Has header "Enter username and password to login"
    //Has 2 textFields for username and password
    //Has a login button that changes to HomeActivity
    //Has a button that changes to AccountCreateActivity
    //Null and whitespace checks for text fields
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
