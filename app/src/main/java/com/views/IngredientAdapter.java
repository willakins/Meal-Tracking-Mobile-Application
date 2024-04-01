package com.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.Ingredient;
import com.model.Recipe;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private ArrayList<Ingredient> pantry;
    Context context;

    public IngredientAdapter(ArrayList<Ingredient> pantry, Context context, RecipeAdapter.OnRecipeClickListener listener) {
        this.pantry = pantry;
        this.context = context;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientNameTxt;

        public IngredientViewHolder(final View view) {
            super(view);
            ingredientNameTxt = view.findViewById(R.id.IngredientName);
        }
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeAdapter.RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
        Ingredient ingredient = pantry.get(position);
        holder.recipeName.setText(ingredient.getName());
        //boolean hasAllIngredients = checkIngredients(recipe.getIngredients());
        boolean hasAllIngredients = true;
        holder.indicator.setImageResource(hasAllIngredients ? R.drawable.ic_check : R.drawable.ic_close);
        holder.itemView.setOnClickListener(v -> {
            // Open recipe details if user has enough ingredients
            if (hasAllIngredients) {
                //listener.onRecipeClick(recipe);
            } else {
                Toast.makeText(context, "Not enough ingredients", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
