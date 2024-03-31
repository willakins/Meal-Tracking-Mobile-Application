package com.example.sprint3tests;

import static junit.framework.TestCase.assertEquals;

import android.content.Context;

import com.model.Ingredient;
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
    @Test
    public void testPantryInputChecks() {
        String testName = "Cheese";
        String testQuantity = "2";
        String testCalories = "130";
        String testExpiration = "4";
        int test1 = PantryViewModel.checkUserInput(testName, testQuantity,
                                                    testCalories, testExpiration);
        testName = "Che ese";
        int test2 = PantryViewModel.checkUserInput(testName, testQuantity,
                testCalories, testExpiration);
        testName = "Cheese";
        testQuantity = "-2";
        int test3 = PantryViewModel.checkUserInput(testName, testQuantity,
                testCalories, testExpiration);
        testQuantity = "2";
        testExpiration = "";
        int test4 = PantryViewModel.checkUserInput(testName, testQuantity,
                testCalories, testExpiration);
        testCalories = "-352";
        int test5 = PantryViewModel.checkUserInput(testName, testQuantity,
                testCalories, testExpiration);
        assertEquals(4, test1);
        assertEquals(0, test2);
        assertEquals(1, test3);
        assertEquals(4, test4);
        assertEquals(2, test5);
    }

    @Test
    public void testPantryInputReceivedByUser() {
        User testUser = new User();
        String testName = "Cheese";
        String testQuantity = "2";
        String testCalories = "130";
        String testExpiration = "4";
        Ingredient newIngredient = new Ingredient(testName, testQuantity, testCalories,
                                                    testExpiration);
        testUser.getPantry().add(newIngredient);
        assertEquals("Cheese", testUser.getPantry().get(0).getName());
    }
}
