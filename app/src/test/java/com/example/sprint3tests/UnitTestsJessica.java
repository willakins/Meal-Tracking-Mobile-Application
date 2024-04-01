package com.example.sprint3tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.model.User;
import com.viewmodels.PantryViewModel;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * @author Jessica Kalloor
 */
public class UnitTestsJessica {
    @Test
    public void testIngredientAddition() {
        PantryViewModel pantryViewModel = PantryViewModel.getInstance();
        User mockUser = new User();
        pantryViewModel.setUser(mockUser);

        // Mock ingredient details
        String name = "Salt";
        String quantity = "200g";
        String calories = "0";
        String expiration = "";

        // Add ingredient
        int result = pantryViewModel.addIngredient(name, quantity, calories, expiration);

        // Check if the ingredient addition was successful
        assertEquals(4, result); // Assuming 4 represents valid input without duplicates
        // Add more assertions as needed to verify pantry updates
    }

    @Test
    public void testInvalidInputHandling() {
        // Provide invalid input for ingredient addition
        String name = ""; // Empty name is invalid
        String quantity = "abc"; // Non-numeric quantity is invalid
        String calories = ""; // Empty calories is invalid
        String expiration = "30a"; // Non-numeric expiration is invalid

        int result = PantryViewModel.checkUserInput(name, quantity, calories, expiration);

        assertEquals(0, result); // Empty name
        assertEquals(1, PantryViewModel.checkUserInput("Sugar", quantity, calories, expiration)); // Invalid quantity
        assertEquals(2, PantryViewModel.checkUserInput("Flour", "500", calories, expiration)); // Empty calories
        assertEquals(3, PantryViewModel.checkUserInput("Rice", "100", "300", expiration)); // Non-numeric expiration
    }
}
