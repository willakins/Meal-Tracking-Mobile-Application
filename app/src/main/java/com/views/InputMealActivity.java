package com.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.views.databinding.ActivityInputMealBinding;


public class InputMealActivity extends AppCompatActivity {
    //Activates when user successfully logs in
    //Has a nav bar that can navigate to: InputMealActivity, RecipeActivity, Ingredient Activity,
    //                                      and Shopping Activity
    // Nav bar should also exist on all 4 of those "main feature" screens
    ActivityInputMealBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityInputMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MealsFragment());

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
