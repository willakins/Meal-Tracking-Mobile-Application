package com.views;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.model.Meal;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.model.User;
import com.viewmodels.LoginViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

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
    private AnyChartView myChart;
    private Pie pie;
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
        userViewModel = UserViewModel.getInstance();
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
        myChart = (AnyChartView) view.findViewById(R.id.any_chart_view);
        pie = AnyChart.pie();
        myChart.setChart(pie);

        //Updates the UI with either default values or values stored in database
        loginViewModel.setMealsFragment(MealsFragment.this);
        updateUI();

        submitMealButton.setOnClickListener(v -> {
            // Saves meal into the database
            String mealName = editMealName.getText().toString();
            String mealCalories = editMealCalories.getText().toString();

            // Check for empty fields
            if (mealName.isEmpty() || mealCalories.isEmpty()) {
                // Display a toast message for empty fields
                Toast.makeText(getContext(), "Please fill in all fields",
                                Toast.LENGTH_SHORT).show();
            } else {
                // Check for invalid input (non-numeric calories)
                if (mealCalories.matches("\\d+")) {
                    // If input is valid, proceed to add meal
                    userViewModel.addUserMeal(mealName, mealCalories);
                    // Updates UI
                    textCaloriesToday.setText("Today's Calories: "
                                                + userViewModel.getUser().getCaloriesToday());
                } else {
                    // Display a toast message for invalid input
                    Toast.makeText(getContext(), "Invalid calories input",
                                    Toast.LENGTH_SHORT).show();
                }
                editMealName.setText("");
                editMealCalories.setText("");
            }
        });

        dataVisual1Button.setOnClickListener(v -> {

            User user = userViewModel.getUser();
            if (user.getMeals().size() == 0) {

                Toast.makeText(MealsFragment.newInstance().getContext(), "No Meals Today",
                        Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Meal> meals = user.getMeals();
                if (!meals.isEmpty()) {
                    List<DataEntry> data = new ArrayList<>();
                    for (int i = 0; i < meals.size(); i++) {
                        data.add(new ValueDataEntry(meals.get(i).getName(),
                                meals.get(i).getCalories()));
                    }
                    pie.data(data);
                } else {
                    Toast.makeText(MealsFragment.newInstance().getContext(), "No Meals Today",
                            Toast.LENGTH_SHORT).show();
                }
            }




        });

        dataVisual2Button.setOnClickListener(v -> {
            User user = userViewModel.getUser();

            List<DataEntry> data = new ArrayList<>();
            data.add(new ValueDataEntry("CALORIC INTAKE", user.getCaloriesToday()));
            data.add(new ValueDataEntry("CALORIES NEEDED", user.getCaloricDeficit()));
            pie.data(data);
            pie.draw(true);
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
    public void updateUI() {
        synchronized (userViewModel.getUser()) {
            textHeight.setText("Height: "
                    + userViewModel.getUser().getHeight());
            textWeight.setText("Weight: "
                    + userViewModel.getUser().getWeight());
            if (loginViewModel.getUser().getIsMale()) {
                textGender.setText("Male");
            } else {
                textGender.setText("Female");
            }
            textCalorieGoal.setText("Calorie Goal: "
                    + Integer.toString(userViewModel.getUser().calculateCalorieGoal()));
            textCaloriesToday.setText("Today's Calories: "
                    + Integer.toString(userViewModel.getUser().getCaloriesToday()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}