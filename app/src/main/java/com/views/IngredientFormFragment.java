package com.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.viewmodels.LoginViewModel;
import com.viewmodels.UserViewModel;

public class IngredientFormFragment extends Fragment {
    private static LoginViewModel loginViewModel;
    private static UserViewModel userViewModel;
    private HomeActivity currentContext;
    private Button goBackButton;
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
        goBackButton = view.findViewById(R.id.goBack);

        goBackButton.setOnClickListener(v -> {
            currentContext.goToIngredients();
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
