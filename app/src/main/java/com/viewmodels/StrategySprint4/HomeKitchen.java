package com.viewmodels.StrategySprint4;

import com.model.StrategySprint4.CookableRecipe;
import com.model.StrategySprint4.Recipe;

public class HomeKitchen extends Kitchen {
    public CookableRecipe createCookableRecipe(Recipe recipe) {
        return new CookableRecipe(recipe.getName(), recipe.getIngredients());
    }
}
