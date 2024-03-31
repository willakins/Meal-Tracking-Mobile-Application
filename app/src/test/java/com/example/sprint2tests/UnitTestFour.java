package com.example.sprint2tests;

import com.model.Meal;
import com.model.User;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;


/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 * @author Ryan Corbett
 */
public class UnitTestFour {

    @Test
    public void testMealInput() {
        User testUser = new User();

        ArrayList<Meal> testMeals = new ArrayList<>();
        Meal testMeal1 = new Meal("meal one", 250);
        Meal testMeal2 = new Meal("meal two", 300);
        testMeals.add(testMeal1);
        testMeals.add(testMeal2);
        testUser.setMeals(testMeals);


        assertEquals(testUser.getMeals(), testMeals);

    }

}
