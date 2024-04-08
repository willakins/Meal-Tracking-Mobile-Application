package com.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.model.Ingredient;
import com.model.User;
import com.viewmodels.PantryViewModel;

import java.util.ArrayList;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter
        .ShoppingViewHolder> {
    private ArrayList<Ingredient> pantry;
    private Context context;
    private Button addButton;
    private Button subtractButton;
    private PantryViewModel pantryViewModel;
    private ShoppingFragment ShoppingFragment;
    private IngredientAdapter.OnIngredientClickListener listener;
    private User user;

    public ShoppingAdapter(ArrayList<Ingredient> pantry, Context context, PantryViewModel pantryViewModel,
                             ShoppingFragment shopFragment) {
        this.pantry = pantry;
        this.context = context;
        this.pantry = pantry;
        this.user = pantryViewModel.getUser();
        this.ShoppingFragment = shopFragment;
        this.pantryViewModel = pantryViewModel;
    }

    @NonNull
    @Override
    public ShoppingAdapter.ShoppingViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_item, parent, false);
        return new ShoppingAdapter.ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoppingAdapter.ShoppingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return pantry.size();
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientNameTxt;
        private Button addButton;
        private Button subtractButton;
        private CheckBox buyButton;

        public ShoppingViewHolder(final View view) {
            super(view);
            ingredientNameTxt = view.findViewById(R.id.IngredientName);
            addButton = view.findViewById(R.id.addButton);
            subtractButton = view.findViewById(R.id.removeButton);
            buyButton = view.findViewById(R.id.buyButton);
        }
    }
}
