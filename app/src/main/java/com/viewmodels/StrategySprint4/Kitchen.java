package com.viewmodels.StrategySprint4;

import com.model.StrategySprint4.CookableRecipe;
import com.model.StrategySprint4.Recipe;

public abstract class Kitchen {
    public Recipe cookRecipe(Recipe recipe) {
        CookableRecipe rec = createCookableRecipe(recipe);
        rec.cook();
        return rec;
    }


    public abstract CookableRecipe createCookableRecipe(Recipe recipe);
}
