package com.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.views.databinding.ActivityInputMealBinding;

/**
 * Center feature screen; contains navbar and has all aspects of fragments
 */
public class InputMealActivity extends AppCompatActivity {
    private ActivityInputMealBinding binding;
    private Button submitMealButton;
    private Button dataVisual1;
    private Button dataVisual2;
    private EditText mealName;
    private EditText mealCalories;

    private EditText height;
    private EditText weight;
    private Switch gender;
    private Button saveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityInputMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MealsFragment());

        submitMealButton = findViewById(R.id.buttonSubmitMeal);
        dataVisual1 = findViewById(R.id.buttonDataVisual1);
        dataVisual2 = findViewById(R.id.buttonDataVisual2);
        saveInfo = findViewById(R.id.buttonSaveInfo);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.meals) {
                replaceFragment(new MealsFragment());
                return true;
            } else if (item.getItemId() == R.id.recipes) {
                replaceFragment(new RecipeFragment());
                return true;
            } else if (item.getItemId() == R.id.ingredients) {
                replaceFragment(new IngredientsFragment());
                return true;
            } else if (item.getItemId() == R.id.shopping) {
                replaceFragment(new ShoppingFragment());
                return true;
            } else if (item.getItemId() == R.id.info) {
                replaceFragment(new PersonalInfoFragment());
                return true;
            }
            return false;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
