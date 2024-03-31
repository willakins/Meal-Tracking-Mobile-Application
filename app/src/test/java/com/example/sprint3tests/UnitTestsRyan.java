package com.example.sprint3tests;

import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.model.Recipe;

import org.junit.Test;

import java.util.ArrayList;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * @author Ryan Corbett
 */
public class UnitTestsRyan {
    @Test
    public void testRecipeName() {
        String RecipeName = "Test Recipe";
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient("test ingredient", "1"));
        ingredients.add(new Ingredient("test ingredient", "1"));
        ingredients.add(new Ingredient("test ingredient", "1"));
        Recipe recipe = new Recipe(RecipeName, ingredients);

        assertEquals(recipe.getName(), "Test Recipe");


    }

    @Test
    public void testRecipeIngredients() {
        String RecipeName = "Test Recipe";
        ArrayList<Ingredient > ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient("test ingredient", "1"));
        ingredients.add(new Ingredient("test ingredient", "2"));
        ingredients.add(new Ingredient("test ingredient", "3"));
        Recipe recipe = new Recipe(RecipeName, ingredients);

        for (int i = 0; i < ingredients.size(); i++) {
            assertEquals("test ingredient", recipe.getIngredients().get(i).getName());
            assertEquals(Integer.toString(i + 1), recipe.getIngredients().get(i).getQuantity());
        }

    }
}
