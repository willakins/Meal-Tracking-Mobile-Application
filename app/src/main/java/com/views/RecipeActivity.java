package com.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {
    private TextView RecipeTextView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);
        RecipeTextView = findViewById(R.id.RecipeTextView);

    }
}
