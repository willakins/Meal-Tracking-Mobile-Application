package com.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.viewmodels.LoginViewModel;
import com.viewmodels.UserViewModel;

public class IngredientsFragment extends Fragment {
    private HomeActivity currentContext;
    private LoginViewModel loginViewModel;
    private UserViewModel userViewModel;
    private Button goToIngredientForm;
    private View view;







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
        //Components of Ingredient Fragment
        goToIngredientForm = view.findViewById(R.id.goToIngredientForm);
        /**
         * TODO: Bind the scrollable list of ingredients here
         */

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
}