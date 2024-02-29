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

import org.w3c.dom.Text;

/**
 * Center feature screen; contains navbar and has all aspects of fragments
 */
public class InputMealActivity extends AppCompatActivity {
    private ActivityInputMealBinding binding;
    private Button submitMealButton;
    private Button dataVisual1;
    private Button dataVisual2;
    private EditText editMealName;
    private EditText editMealCalories;
    private TextView textHeight;
    private TextView textWeight;
    private TextView textGender;
    private TextView textCalorieGoal;
    private TextView textCaloriesToday;

    private EditText editHeight;
    private EditText editWeight;
    private Switch switchGender;
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
        editMealName = findViewById(R.id.editMealName);
        editMealCalories = findViewById(R.id.editTextCalories);
        editHeight = findViewById(R.id.editTextHeight);
        editWeight = findViewById(R.id.editTextWeight);
        switchGender = findViewById(R.id.switchGender);
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

        /**
         * TODO: Should save data from mealName and mealCalories and send it to database
         * TODO: Should also clear text fields and check for invalid input
         */
        submitMealButton.setOnClickListener(v -> {

        });

        /**
         * TODO: Should display calorie data using imported library of choosing
         */
        dataVisual1.setOnClickListener(v -> {

        });

        /**
         * TODO: Should display calorie data using imported library of choosing
         */
        dataVisual2.setOnClickListener(v -> {

        });

        /**
         * TODO: Should save data from 2 edit texts and switch and send it to database
         * TODO: Should set this data in other tabs like input meals screen
         * TODO: Should check user input for null and invalid
         */
        saveInfo.setOnClickListener(v -> {
            //...
            //textHeight.setText(newHeight);
        });

    }

    /**
     * Helper method that changes the fragment visual currently on screen
     * @param fragment the new screen fragment to be displayed
     */
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
