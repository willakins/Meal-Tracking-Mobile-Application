package com.viewmodels.StrategySprint4;

import com.model.StrategySprint4.CookableRecipe;
import com.model.StrategySprint4.Recipe;
import com.viewmodels.UserViewModel;

public abstract class Kitchen {
    public Recipe cookRecipe(CookableRecipe recipe, UserViewModel userViewModel) {
        CookableRecipe rec = createCookableRecipe(recipe);
        rec.cook(userViewModel);
        userViewModel.getUser().addCalories(recipe.getCalories());
        userViewModel.updateCalories();
        return rec;
    }


    public abstract CookableRecipe createCookableRecipe(Recipe recipe);
}
