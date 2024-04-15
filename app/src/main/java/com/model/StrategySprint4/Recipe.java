package com.model.StrategySprint4;

import com.model.Ingredient;

import java.util.ArrayList;

public interface Recipe {

    public int calculateCalories();

    public String getName();

    ArrayList<Ingredient> getIngredients();

    void setName(String name);

    void setIngredients(ArrayList<Ingredient> ingredients);

    int getCalories();

}
