package com.views;

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
                RecipeFragment recFrag = new RecipeFragment();
                recFrag.setContext(HomeActivity.this);
                replaceFragment(recFrag);
                return true;
            } else if (item.getItemId() == R.id.ingredients) {
                IngredientsFragment ingFragment = new IngredientsFragment();
                ingFragment.setContext(HomeActivity.this);
                replaceFragment(ingFragment);
                return true;
            } else if (item.getItemId() == R.id.shopping) {
                ShoppingFragment shopFrag = new ShoppingFragment();
                shopFrag.setContext(HomeActivity.this);
                replaceFragment(shopFrag);
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
        IngredientFormFragment formFrag = new IngredientFormFragment();
        formFrag.setContext(HomeActivity.this);
        replaceFragment(formFrag);
    }

    public void goToIngredients() {
        IngredientsFragment ingFrag = new IngredientsFragment();
        ingFrag.setContext(HomeActivity.this);
        replaceFragment(ingFrag);
    }

    public void goToShoppingForm() {
        ShoppingFormFragment formFrag = new ShoppingFormFragment();
        formFrag.setContext(HomeActivity.this);
        replaceFragment(formFrag);
    }

    public void goToShopping() {
        ShoppingFragment shopFrag = new ShoppingFragment();
        shopFrag.setContext(HomeActivity.this);
        replaceFragment(shopFrag);
    }

}
