package com.example;

import com.model.User;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 * @author Ryan Corbett
 */
public class UnitTestThree {

    @Test
    public void testCaloricDeficit() {
        User testUser = new User();
        int testHeight = 71;
        int testWeight = 175;
        testUser.setHeight(testHeight);
        testUser.setWeight(testWeight);
        testUser.setIsMale(true);
        testUser.setCalorieGoal(testUser.calculateCalorieGoal());
        testUser.setCaloriesToday(1500);
        int deficit = testUser.getCalorieGoal() - testUser.getCaloriesToday();

        assertEquals(testUser.getCaloricDeficit(), deficit);
        
    }
}
