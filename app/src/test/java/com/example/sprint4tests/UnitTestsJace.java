package com.example.sprint4tests;

import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.model.StrategySprint4.CookableRecipe;
import com.model.User;
import com.viewmodels.StrategySprint4.HomeKitchen;
import com.viewmodels.UserViewModel;

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
    public void testAddIngredientToPantry() {
        User user = new User("jacewalden3@gmail.com", "123456");
        Ingredient testChicken = new Ingredient("tortillas", "2");
        Ingredient testRice = new Ingredient("beef", "4");
        user.getPantry().add(testChicken);
        user.getPantry().add(testRice);

        assertEquals(2, user.getPantry().size());
    }

    @Test
    public void testRemoveIngredientFromPantry() {
        User user = new User("jacewalden3@gmail.com", "123456");
        Ingredient testChicken = new Ingredient("Chicken", "2");
        Ingredient testRice = new Ingredient("Rice", "4");
        user.getPantry().add(testChicken);
        user.getPantry().add(testRice);

        user.getPantry().remove(testChicken);
        user.getPantry().remove(testRice);

        assertEquals(0, user.getPantry().size());
    }
}
