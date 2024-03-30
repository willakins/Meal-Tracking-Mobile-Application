package com.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.viewmodels.CookBookViewModel;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

public class RecipeFragment extends Fragment {
    private static LoginViewModel loginViewModel;
    private static UserViewModel userViewModel;
    private static CookBookViewModel cookBook;
    private static PantryViewModel pantry;
    private Button submitRecipeButton;
    private EditText editTextRecipeName;
    private EditText editTextIngredients;
    private HomeActivity currentContext;
    private View view;

    public RecipeFragment() {
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
        view = inflater.inflate(R.layout.fragment_recipe, container, false);
        //Components of Recipe page
        loginViewModel = LoginViewModel.getInstance();
        userViewModel = UserViewModel.getInstance();
        cookBook = CookBookViewModel.getInstance();
        pantry = PantryViewModel.getInstance();
        submitRecipeButton = view.findViewById(R.id.buttonSaveRecipe);
        editTextRecipeName = view.findViewById(R.id.editTextRecipeName);
        editTextIngredients = view.findViewById(R.id.editTextIngredients);
        /**
         * TODO 1: Bind the scrollable list of recipes here
         * Then actually create the scrollable list somewhere else
         */

        /**
         * TODO 2: Implement addRecipe function in CookBookViewModel to parse the data
         * and add it to the database
         */
        submitRecipeButton.setOnClickListener(v -> {
            cookBook.addRecipe(getContext(), editTextRecipeName, editTextIngredients);
            editTextRecipeName.setText("");
            editTextIngredients.setText("");
        });

        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecipeFragment.
     */
    public static RecipeFragment newInstance() {
        RecipeFragment fragment = new RecipeFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setContext(HomeActivity context) {
        this.currentContext = context;
    }
}