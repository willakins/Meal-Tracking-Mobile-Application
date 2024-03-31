package com.model.Strategy;

import com.model.Ingredient;
import com.model.Recipe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterByAvailableIngredientsStrategy implements RecipeManipulationStrategy {
    @Override
    public List<Recipe> execute(List<Recipe> recipes, Map<String, Ingredient> pantry) {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .allMatch(ingredient -> pantry.containsKey(ingredient.getName()) &&
                                Integer.parseInt(pantry.get(ingredient.getName()).getQuantity()) >= Integer.parseInt(ingredient.getQuantity())))
                .collect(Collectors.toList());
    }
}
