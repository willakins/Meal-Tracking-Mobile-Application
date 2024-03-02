package com.views;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.model.Meal;
import com.viewmodels.LoginViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class MealsFragment extends Fragment {
    private Button submitMealButton;
    private Button dataVisual1Button;
    private Button dataVisual2Button;
    private EditText editMealName;
    private EditText editMealCalories;
    private TextView textHeight;
    private TextView textWeight;
    private TextView textGender;
    private TextView textCalorieGoal;
    private TextView textCaloriesToday;
    private static LoginViewModel loginViewModel;
    private static UserViewModel userViewModel;

    private View view;

    public MealsFragment() {
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
        view = inflater.inflate(R.layout.fragment_meals, container, false);
        loginViewModel = LoginViewModel.getInstance();
        //Components of Input Meals Page
        submitMealButton = view.findViewById(R.id.buttonSubmitMeal);
        dataVisual1Button = view.findViewById(R.id.buttonDataVisual1);
        dataVisual2Button = view.findViewById(R.id.buttonDataVisual2);
        editMealName = view.findViewById(R.id.editMealName);
        editMealCalories = view.findViewById(R.id.editTextCalories);
        textHeight = view.findViewById(R.id.textViewHeight);
        textWeight = view.findViewById(R.id.textViewWeight);
        textGender = view.findViewById(R.id.textViewGender);
        textCalorieGoal = view.findViewById(R.id.textViewCalorieGoal);
        textCaloriesToday = view.findViewById(R.id.textViewCaloriesToday);
        userViewModel = UserViewModel.getInstance();

        initializeDefaults();

        /**
         * TODO 1: Should save data from mealName and mealCalories and send it to database
         * TODO 1: Should also clear text fields and check for invalid input
         */
        submitMealButton.setOnClickListener(v -> {
            //Saves meal into database
            String mealName = editMealName.getText().toString();
            String mealCalories = editMealCalories.getText().toString();
            userViewModel.addUserMeal(mealName, mealCalories);
            //Updates UI
            textCaloriesToday.setText("Today's Calories: " + userViewModel.getUser().getCaloriesToday());
        });

        /**
         * TODO 2: Should display calorie data using imported library of choosing
         */
        dataVisual1Button.setOnClickListener(v -> {

        });

        /**
         * TODO 2: Should display calorie data using imported library of choosing
         */
        dataVisual2Button.setOnClickListener(v -> {

        });

        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MealsFragment.
     */
    public static MealsFragment newInstance() {
        MealsFragment fragment = new MealsFragment();
        return fragment;
    }

    /**
     * Helper method to abstract the process of initializing the text views before user input
     */
    private void initializeDefaults() {
        textHeight.setText("Height: " +
                                Integer.toString(loginViewModel.getUser().getHeight()));
        textWeight.setText("Weight: " +
                                Integer.toString(loginViewModel.getUser().getWeight()));
        if (loginViewModel.getUser().getIsMale()) {
            textGender.setText("Male");
        } else {
            textGender.setText("Female");
        }
        textCalorieGoal.setText("Calorie Goal: " +
                                Integer.toString(loginViewModel.getUser().calculateCalorieGoal()));
        textCaloriesToday.setText("Today's Calories: " +
                                Integer.toString(loginViewModel.getUser().getCaloriesToday()));
    }
}