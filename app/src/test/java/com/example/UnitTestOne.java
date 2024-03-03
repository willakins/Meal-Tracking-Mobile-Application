package com.example;

import com.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * Test that the User model correctly calculates calorie goal
 * @author Will Akins
 */
public class UnitTestOne {

    @Test
    public void testCalorieCalculate() {
        String testUsername = "willhakins@gmail.com";
        String testPassword = "william";
        int testHeight = 73;
        int testWeight = 165;
        int expectedCalories = (int) Math.ceil((10 * testWeight) + (6.25 * testHeight) - 55);
        User testUser = new User(testUsername, testPassword);
        testUser.setHeight(testHeight);
        testUser.setWeight(testWeight);
        testUser.setIsMale(true);
        testUser.setCalorieGoal(testUser.calculateCalorieGoal());
        assertEquals(expectedCalories, testUser.getCalorieGoal());
    }
}
