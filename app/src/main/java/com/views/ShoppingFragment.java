package com.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.model.StrategySprint4.Recipe;
import com.model.ShoppingItem;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment {
    private HomeActivity currentContext;
    private LoginViewModel loginViewModel;
    private UserViewModel userViewModel;
    private PantryViewModel pantryViewModel;
    private EditText shoppingName;
    private EditText shoppingQuantity;
    private EditText shoppingCalories;
    private Button goToForm;
    private Button purchase;
    private View view;
    private RecyclerView shoppingRecyclerView;
    private ShoppingAdapter shoppingAdapter;

    public ShoppingFragment() {
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
        view = inflater.inflate(R.layout.fragment_shopping, container, false);
        loginViewModel = LoginViewModel.getInstance();
        userViewModel = UserViewModel.getInstance();
        pantryViewModel = PantryViewModel.getInstance();

        ArrayList<Recipe> recipes = userViewModel.getUser().getCookBook();
        shoppingRecyclerView = view.findViewById(R.id.shoppingRecyclerView);
        shoppingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        shoppingAdapter = new ShoppingAdapter(getContext(), this);
        shoppingRecyclerView.setAdapter(shoppingAdapter);
        shoppingName = view.findViewById(R.id.editTextShoppingName);
        shoppingQuantity = view.findViewById(R.id.editTextShoppingQuantity);
        shoppingCalories = view.findViewById(R.id.editTextShoppingCalories);
        goToForm = view.findViewById(R.id.buttonShopping);
        purchase = view.findViewById(R.id.buttonPurchase);

        purchase.setOnClickListener(v -> {
            ArrayList<ShoppingItem> checkedItems = shoppingAdapter.getCheckedItems();
            if (checkedItems.size() != 0) {
                userViewModel.updateItems(checkedItems);
                generateNewAdapter();
            }
        });

        goToForm.setOnClickListener(v -> {
            currentContext.goToShoppingForm();
        });

        return view;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoppingFragment.
     */
    public static ShoppingFragment newInstance() {
        ShoppingFragment fragment = new ShoppingFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setContext(HomeActivity context) {
        this.currentContext = context;
    }

    public void generateNewAdapter() {
        shoppingAdapter =  new ShoppingAdapter(getContext(), this);
        shoppingRecyclerView.setAdapter(shoppingAdapter);
    }
}