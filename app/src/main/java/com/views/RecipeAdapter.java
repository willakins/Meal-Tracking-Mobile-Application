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
import com.model.StrategySprint4.Recipe;
import com.model.User;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private UserViewModel userViewModel;
    private ArrayList<Recipe> recipes;
    private ArrayList<Ingredient> pantry;
    private Context context;
    private OnRecipeClickListener listener;

    public RecipeAdapter(ArrayList<Recipe> recipes, ArrayList<Ingredient> pantry,
                         Context context, OnRecipeClickListener listener) {
        this.recipes = recipes;
        this.pantry = pantry;
        this.context = context;
        this.listener = listener;
        userViewModel = UserViewModel.getInstance();
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
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
        User user = userViewModel.getUser();
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                int pantryIndex = user.locateIngredient(ingredient);
                if (pantryIndex != -1) {
                    if (Integer.parseInt(user.getPantry().get(pantryIndex).getQuantity())
                            < Integer.parseInt(ingredient.getQuantity())) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateRecipes(ArrayList<Recipe> newRecipes) {
        this.recipes = newRecipes;
    }

    public void setPantryItems(ArrayList<Ingredient> pantryItems) {
        this.pantry = pantryItems;
    }

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        private ImageView indicator;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            indicator = itemView.findViewById(R.id.indicator);
        }
    }
}