package com.model.Strategy;

import com.model.Ingredient;
import com.model.StrategySprint4.Recipe;

import java.util.List;
import java.util.Map;

public interface RecipeManipulationStrategy {
    List<Recipe> execute(List<Recipe> recipes, Map<String, Ingredient> pantry);
}
