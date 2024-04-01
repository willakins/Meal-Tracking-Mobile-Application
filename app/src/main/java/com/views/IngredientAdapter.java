package com.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.Ingredient;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private ArrayList<Ingredient> ingredientList;

    public IngredientAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void increaseQuantity(int position) {
        Ingredient ingredient = ingredientList.get(position);
        int quantity = Integer.parseInt(ingredient.getQuantity());
        quantity++;
        ingredient.setQuantity(String.valueOf(quantity));
        notifyItemChanged(position);
    }

    public void decreaseQuantity(int position) {
        Ingredient ingredient = ingredientList.get(position);
        int quantity = Integer.parseInt(ingredient.getQuantity());
        if (quantity > 0) {
            quantity--;
            ingredient.setQuantity(String.valueOf(quantity));
            notifyItemChanged(position);
        }
        // If quantity becomes zero, you may remove the ingredient here
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView quantityTextView;
        private Button increaseButton;
        private Button decreaseButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.ingredientName);
            quantityTextView = itemView.findViewById(R.id.ingredientQuantity);
            increaseButton = itemView.findViewById(R.id.increaseButton);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
        }

        public void bind(Ingredient ingredient) {
            nameTextView.setText(ingredient.getName());
            quantityTextView.setText(ingredient.getQuantity());

            increaseButton.setOnClickListener(v -> {
                increaseQuantity(getAdapterPosition());
            });

            decreaseButton.setOnClickListener(v -> {
                decreaseQuantity(getAdapterPosition());
            });
        }
    }
}
