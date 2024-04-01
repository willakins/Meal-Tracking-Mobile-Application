package com.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.model.Ingredient;
import com.model.User;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter
        .IngredientViewHolder> {
    private ArrayList<Ingredient> pantry;
    private Context context;
    private Button addButton;
    private Button subtractButton;
    private UserViewModel userViewModel;
    private IngredientAdapter.OnIngredientClickListener listener;
    private User user;

    public IngredientAdapter(ArrayList<Ingredient> pantry, Context context,
                             IngredientAdapter.OnIngredientClickListener listener,
                             UserViewModel userViewModel) {
        this.pantry = pantry;
        this.context = context;
        this.userViewModel = userViewModel;
        this.user = userViewModel.getUser();
    }

    @NonNull
    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientAdapter.IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.IngredientViewHolder holder, int position) {
        Ingredient ingredient = pantry.get(position);
        holder.ingredientNameTxt.setText(ingredient.getName() + " : "
                + ingredient.getQuantity());
        holder.addButton.setOnClickListener(v -> {
            DatabaseReference mDatabase = userViewModel.getDatabase();
            int pantryIndex = user.locateIngredient(ingredient);
            if (pantryIndex != -1) {
                String newQuantity = Integer.toString(Integer.parseInt(user.getPantry().get(pantryIndex).getQuantity()) + 1);
                user.getPantry().get(pantryIndex).setQuantity(newQuantity);
                mDatabase.child("pantry").child(user.getUserId()).child(ingredient.getName())
                        .child("Quantity").setValue(newQuantity);
            }
            holder.ingredientNameTxt.setText(ingredient.getName() + " : "
                    + ingredient.getQuantity());

        });

        holder.subtractButton.setOnClickListener(v -> {
            DatabaseReference mDatabase = userViewModel.getDatabase();
            int pantryIndex = user.locateIngredient(ingredient);
            if (pantryIndex != -1) {
                String newQuantity = Integer.toString(Integer.parseInt(user.getPantry().get(pantryIndex).getQuantity()) - 1);
                user.getPantry().get(pantryIndex).setQuantity(newQuantity);
                mDatabase.child("pantry").child(user.getUserId()).child(ingredient.getName())
                        .child("Quantity").setValue(newQuantity);
            }
            holder.ingredientNameTxt.setText(ingredient.getName() + " : "
                    + ingredient.getQuantity());
        });
    }

    @Override
    public int getItemCount() {
        return pantry.size();
    }

    public interface OnIngredientClickListener {
        void onIngredientClick(Ingredient ingredient);
    }



    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientNameTxt;
        private Button addButton;
        private Button subtractButton;

        public IngredientViewHolder(final View view) {
            super(view);
            ingredientNameTxt = view.findViewById(R.id.IngredientName);
            addButton = view.findViewById(R.id.addButton);
            subtractButton = view.findViewById(R.id.removeButton);
        }
    }
}
