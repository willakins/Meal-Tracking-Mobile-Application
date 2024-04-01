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
 * @author
 */
public class UnitTestsJace {
    @Test
    public void testIngredientNameAssignment() {
        Ingredient testIngredient = new Ingredient("test", "x");
        assertEquals("test", testIngredient.getName());
    }

    @Test
    public void testRecipeNameModification() {
        String testRecipeName = "recipe";
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient("tomatoes", "1"));
        ingredients.add(new Ingredient("onions", "2"));
        ingredients.add(new Ingredient("lettuce", "3"));
        Recipe testRecipe = new Recipe(testRecipeName, ingredients);

        testRecipe.setName("newRecipe");

        assertEquals(testRecipe.getName(), "newRecipe");

    }
}
