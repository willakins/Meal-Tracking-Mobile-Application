package com.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.model.Ingredient;
import com.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private Map<String, Ingredient> pantry;
    private Context context;
    private OnRecipeClickListener listener;
    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

    public RecipeAdapter(List<Recipe> recipes, Map<String, Ingredient> pantry, Context context,
                         OnRecipeClickListener listener) {
        this.recipes = recipes;
        this.pantry = pantry;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.getName());
        boolean hasAllIngredients = checkIngredients(recipe.getIngredients());
        holder.indicator.setImageResource(hasAllIngredients ? R.drawable.ic_check
                : R.drawable.ic_close);
        holder.itemView.setOnClickListener(v -> {
            // Open recipe details if user has enough ingredients
            if (hasAllIngredients) {
                listener.onRecipeClick(recipe);
            } else {
                Toast.makeText(context, "Not enough ingredients", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    private boolean checkIngredients(ArrayList<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (!pantry.containsKey(ingredient.getName())
                    || Integer.parseInt(pantry.get(ingredient.getName()).getQuantity())
                            < Integer.parseInt(ingredient.getQuantity())) {
                return false;
            }
        }
        return true;
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        private ImageView indicator;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            indicator = itemView.findViewById(R.id.indicator);
        }
    }

    public void updateRecipes(List<Recipe> newRecipes) {
        this.recipes = newRecipes;
    }

    public void setPantryItems(Map<String, Ingredient> pantryItems) {
        this.pantry = pantryItems;
    }
}