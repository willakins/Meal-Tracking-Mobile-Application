package com.example.sprint2tests;

import com.model.Meal;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * testing illegal arguments
 * @author Jessica Kalloor
 */
public class UnitTestSix {
    @Test(expected = IllegalArgumentException.class)
    public void testMealConstructor_NegativeCalories() {
        Meal meal = new Meal("Snack", -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMealConstructor_EmptyName() {
        Meal meal = new Meal("", 200);
    }
}
