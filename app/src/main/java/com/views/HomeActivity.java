package com.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.views.databinding.ActivityHomeBinding;
/**
 * Center feature screen; contains navbar and has all aspects of fragments
 */
public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.meals) {
                replaceFragment(new MealsFragment());
                return true;
            } else if (item.getItemId() == R.id.recipes) {
                replaceFragment(new RecipeFragment());
                return true;
            } else if (item.getItemId() == R.id.ingredients) {
                IngredientsFragment ingFragment = new IngredientsFragment();
                ingFragment.setHomeInstance(HomeActivity.this);
                replaceFragment(ingFragment);
                return true;
            } else if (item.getItemId() == R.id.shopping) {
                replaceFragment(new ShoppingFragment());
                return true;
            } else if (item.getItemId() == R.id.info) {
                PersonalInfoFragment pf = new PersonalInfoFragment();
                pf.setContext(HomeActivity.this);
                replaceFragment(pf);
                return true;
            }
            return false;
        });
    }

    /**
     * Helper method that changes the fragment visual currently on screen
     * @param fragment the new screen fragment to be displayed
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void goToIngredientForm() {
        replaceFragment(new IngredientFormFragment());
    }

}
