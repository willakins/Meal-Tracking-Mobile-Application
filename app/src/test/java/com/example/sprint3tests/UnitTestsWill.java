package com.example.sprint3tests;

import static junit.framework.TestCase.assertEquals;

import android.content.Context;

import com.model.User;
import com.viewmodels.PantryViewModel;
import com.views.HomeActivity;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * Tests if the PantryViewModel does correct null and whitespace checks for data
 *
 * @author Will Akins
 */
public class UnitTestsWill {
    PantryViewModel pantry = PantryViewModel.getInstance();
    User testUser = new User();
    pantry.setUser(testUser);
    Context context = new HomeActivity();
    @Test
    public void testPantryInputChecks() {

    }

    @Test
    public void testPantryInputReceivedByUser() {
        String testName = "Cheese";
        String testQuantity = "2";
        String testCalories = "130";
        String testExpiration = "4";
        pantry.addIngredient(context, testName, testQuantity, testCalories, testExpiration);
        assertEquals("Cheese", )
    }
}
