package com.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.model.Recipe;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment {
    private HomeActivity currentContext;
    private LoginViewModel loginViewModel;
    private UserViewModel userViewModel;
    private PantryViewModel pantryViewModel;
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
        shoppingAdapter = new ShoppingAdapter(userViewModel.getUser().getPantry(), getContext(),
                                pantryViewModel, this);
        shoppingRecyclerView.setAdapter(shoppingAdapter);

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
}