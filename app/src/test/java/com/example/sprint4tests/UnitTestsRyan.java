package com.example.sprint4tests;

import static org.junit.Assert.assertEquals;
import com.model.Ingredient;
import com.model.StrategySprint4.CookableRecipe;
import com.model.User;

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
    public void testPantryDiscrepancy() {
        User user = new User("4ryan@thecorbetts.org", "password");
        Ingredient testChicken = new Ingredient("Chicken", "1");
        Ingredient testRice = new Ingredient("Rice", "1");
        user.getPantry().add(testChicken);
        user.getPantry().add(testRice);

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient chicken = new Ingredient("Chicken", "2");
        Ingredient rice = new Ingredient("Rice", "2");
        ingredients.add(chicken);
        ingredients.add(rice);
        CookableRecipe recipe = new CookableRecipe("Chicken and Rice", ingredients);

        int chickenDifference = Integer.parseInt(recipe.getIngredients().get(0).getQuantity())
                - Integer.parseInt(user.getPantry().get(0).getQuantity());
        int riceDifference = Integer.parseInt(recipe.getIngredients().get(1).getQuantity())
                - Integer.parseInt(user.getPantry().get(1).getQuantity());

        assertEquals(1, chickenDifference);
        assertEquals(1, riceDifference);
    }

    @Test
    public void testTwo() {

    }
}
