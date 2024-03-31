package com.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

public class IngredientFormFragment extends Fragment {
    private static LoginViewModel loginViewModel;
    private static UserViewModel userViewModel;
    private static PantryViewModel pantry;
    private HomeActivity currentContext;
    private Button goBackButton;
    private Button submitIngredientButton;
    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextCalories;
    private EditText editTextExpiration;
    private View view;

    public IngredientFormFragment() {
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
        view = inflater.inflate(R.layout.fragment_ingredient_form, container, false);
        //Components of Ingredient Fragment
        loginViewModel = LoginViewModel.getInstance();
        userViewModel = UserViewModel.getInstance();
        pantry = PantryViewModel.getInstance();
        goBackButton = view.findViewById(R.id.goBack);
        submitIngredientButton = view.findViewById(R.id.buttonSubmitIngredient);
        editTextName = view.findViewById(R.id.editTextName);
        editTextQuantity = view.findViewById(R.id.editTextQuantity);
        editTextCalories = view.findViewById(R.id.editTextCalories);
        editTextExpiration = view.findViewById(R.id.editTextExpiration);

        goBackButton.setOnClickListener(v -> {
            currentContext.goToIngredients();
        });

        submitIngredientButton.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String quantity = editTextQuantity.getText().toString();
            String calories = editTextCalories.getText().toString();
            String expiration = editTextExpiration.getText().toString();
            int validInput = pantry.addIngredient(name, quantity,
                                    calories, expiration);
            if (validInput == 0) {
                Toast.makeText(currentContext, "Invalid name input",
                        Toast.LENGTH_SHORT).show();
            } else if (validInput == 1) {
                Toast.makeText(currentContext, "Invalid quantity input",
                        Toast.LENGTH_SHORT).show();
            } else if (validInput == 2) {
                Toast.makeText(currentContext, "Invalid calories input",
                        Toast.LENGTH_SHORT).show();
            } else if (validInput == 3) {
                Toast.makeText(currentContext, "Invalid expiration input",
                        Toast.LENGTH_SHORT).show();
            } else if (validInput == 5) {
                Toast.makeText(currentContext, "Cannot add ingredients already in pantry",
                        Toast.LENGTH_SHORT).show();
            }
            editTextName.setText("");
            editTextQuantity.setText("");
            editTextCalories.setText("");
            editTextExpiration.setText("");
        });


        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MealsFragment.
     */
    public static IngredientFormFragment newInstance() {
        IngredientFormFragment formFragment = new IngredientFormFragment();
        return formFragment;
    }

    public void setContext(HomeActivity context) {
        this.currentContext = context;
    }
}
