package com.example.sprint2tests;

import com.model.Ingredient;
import com.model.Recipe;
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
public class UnitTestThree {

    //test
    @Test
    public void testCaloricDeficit() {
        User testUser = new User();
        String testHeight = "71";
        String testWeight = "175";
        testUser.setHeight(testHeight);
        testUser.setWeight(testWeight);
        testUser.setIsMale(true);
        testUser.setCalorieGoal(testUser.calculateCalorieGoal());
        testUser.setCaloriesToday(1500);
        int deficit = testUser.getCalorieGoal() - testUser.getCaloriesToday();

        assertEquals(testUser.getCaloricDeficit(), deficit);
        
    }


}
