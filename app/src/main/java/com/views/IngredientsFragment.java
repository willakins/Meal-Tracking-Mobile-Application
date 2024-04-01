package com.views;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.model.Ingredient;
import com.model.Recipe;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {
    private HomeActivity currentContext;
    private LoginViewModel loginViewModel;
    private UserViewModel userViewModel;
    private PantryViewModel pantryViewModel;
    private Button goToIngredientForm;
    private View view;
    private RecyclerView ingredientsRecyclerView;
    private IngredientAdapter ingredientAdapter;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        loginViewModel = LoginViewModel.getInstance();
        userViewModel = UserViewModel.getInstance();
        pantryViewModel = PantryViewModel.getInstance();
        //Components of Ingredient Fragment
        goToIngredientForm = view.findViewById(R.id.goToIngredientForm);
        ArrayList<Recipe> recipes = userViewModel.getUser().getCookBook();
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientAdapter = new IngredientAdapter(userViewModel.getUser().getPantry(), getContext(),
                ingredient -> openRecipeDetails(ingredient), pantryViewModel, this);
        ingredientsRecyclerView.setAdapter(ingredientAdapter);
        goToIngredientForm = view.findViewById(R.id.goToIngredientForm);

        goToIngredientForm.setOnClickListener(v -> {
            currentContext.goToIngredientForm();
        });
        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MealsFragment.
     */
    public static IngredientsFragment newInstance() {
        IngredientsFragment fragment = new IngredientsFragment();
        return fragment;
    }

    public void setContext(HomeActivity context) {
        this.currentContext = context;
    }

    private void openRecipeDetails(Ingredient ingredient) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(ingredient.getName());
        builder.setMessage("Total Calories: " + ingredient.getCalories());
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void generateNewAdapter() {
        ingredientAdapter = new IngredientAdapter(userViewModel.getUser().getPantry(), getContext(),
                ingredient -> openRecipeDetails(ingredient), pantryViewModel, this);
        ingredientsRecyclerView.setAdapter(ingredientAdapter);
    }
}