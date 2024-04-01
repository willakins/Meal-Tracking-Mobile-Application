package com.example.sprint3tests;

import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.model.User;
import com.viewmodels.PantryViewModel;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * @Christine Lee
 */
public class UnitTestsChristine {
    @Test
    public void testDuplicateIngredients() {
        PantryViewModel pantryViewModel = PantryViewModel.getInstance();
        User testUser = new User();
        pantryViewModel.setUser(testUser);

        // Test Ingredient Details
        String name = "Butter";
        String quantity = "100g";
        String calories = "30";
        String expiration = "";

        // Add Ingredient
        pantryViewModel.addIngredient(name, quantity, calories, expiration);

        // Second Test Ingredient Details
        String secondName = "Butter";
        String secondQuantity = "100g";
        String secondCalories = "30";
        String secondExpiration = "";

        //Add Second Ingredient
        int result = pantryViewModel.addIngredient(secondName, secondQuantity, secondCalories,
                secondExpiration);

        // Check if the second ingredient failed
        assertEquals(5, result);
    }

    @Test
    public void testIngredientExpiration() {
        Ingredient testIngredient = new Ingredient("Butter", "1",
                "30", "20");
        assertEquals("20", testIngredient.getExpiration());
    }

}
