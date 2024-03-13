package com.example;

import static org.junit.Assert.assertEquals;

import com.model.User;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * testing if default values are working when calculating calorie goal! through this test
 * we found that the calculate calorie goal had wrong numbers so we had to adjust
 * accordingly
 * @author Christine Lee
 */
public class UnitTestTwelve {

    @Test
    public void testNoMeals() {
        String testUsername = "unis4christine@gmail.com";
        String testPassword = "christine";
        User testUser = new User(testUsername, testPassword);
        testUser.setCalorieGoal(testUser.calculateCalorieGoal());
        assertEquals(1439, testUser.getCalorieGoal());
    }
}
