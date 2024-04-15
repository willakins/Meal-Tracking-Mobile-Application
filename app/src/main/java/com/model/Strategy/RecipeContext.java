package com.model.Strategy;

import com.model.Ingredient;
import com.model.StrategySprint4.Recipe;

import java.util.List;
import java.util.Map;

public class RecipeContext {
    private RecipeManipulationStrategy strategy;

    public RecipeContext(RecipeManipulationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RecipeManipulationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Recipe> executeStrategy(List<Recipe> recipes, Map<String, Ingredient> pantry) {
        return strategy.execute(recipes, pantry);
    }
}
