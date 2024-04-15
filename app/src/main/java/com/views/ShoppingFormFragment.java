package com.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

public class ShoppingFormFragment extends Fragment {
    private HomeActivity currentContext;
    private UserViewModel userViewModel;
    private EditText shoppingName;
    private EditText shoppingQuantity;
    private EditText shoppingCalories;
    private Button addToList;
    private Button goBack;
    private View view;

    public ShoppingFormFragment() {
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
        view = inflater.inflate(R.layout.fragment_shopping_form, container, false);
        userViewModel = UserViewModel.getInstance();
        shoppingName = view.findViewById(R.id.editTextShoppingName);
        shoppingQuantity = view.findViewById(R.id.editTextShoppingQuantity);
        shoppingCalories = view.findViewById(R.id.editTextShoppingCalories);
        addToList = view.findViewById(R.id.buttonAddToList);
        goBack = view.findViewById(R.id.buttonGoBackShopping);

        addToList.setOnClickListener(v -> {
            String itemName = shoppingName.getText().toString();
            String quantity = shoppingQuantity.getText().toString();
            String calories = shoppingCalories.getText().toString();
            shoppingName.setText("");
            shoppingQuantity.setText("");
            shoppingCalories.setText("");
            int validInput = userViewModel.addShoppingItem(itemName, quantity, calories);
            if (validInput == 1) {
                Toast.makeText(currentContext, "Invalid name input",
                        Toast.LENGTH_SHORT).show();
            } else if (validInput == 2) {
                Toast.makeText(currentContext, "Invalid quantity input",
                        Toast.LENGTH_SHORT).show();
            } else if (validInput == 3) {
                Toast.makeText(currentContext, "Invalid calories input",
                        Toast.LENGTH_SHORT).show();
            }
        });

        goBack.setOnClickListener(v -> {
            currentContext.goToShopping();
        });


        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoppingFragment.
     */
    public static ShoppingFormFragment newInstance() {
        ShoppingFormFragment fragment = new ShoppingFormFragment();
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