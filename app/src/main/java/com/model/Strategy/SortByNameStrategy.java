package com.model.Strategy;

import com.model.Ingredient;
import com.model.Recipe;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortByNameStrategy implements RecipeManipulationStrategy {
    @Override
    public List<Recipe> execute(List<Recipe> recipes, Map<String, Ingredient> pantry) {
        recipes.sort(Comparator.comparing(Recipe::getName));
        return recipes;
    }
}