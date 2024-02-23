package com.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class InputMealActivity extends AppCompatActivity {

    private TextView InputMealTextView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_meal);
        InputMealTextView = findViewById(R.id.InputMealTextView);

    }
}
