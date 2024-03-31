package com.example.sprint2tests;

import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.model.Meal;
import com.model.User;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 * @author Jace Walden
 */
public class UnitTestEight {
//    @Test
//    public void testHeightAndWeightUpdate() {
//        String testHeight = "72";
//        String testWeight = "220";
//        User testUser = new User();
//        testUser.setHeight(testHeight);
//        testUser.setWeight(testWeight);
//
//        assertEquals(testHeight, testUser.getHeight());
//        assertEquals(testWeight, testUser.getWeight());
//    }

    @Test
    public void testIngredientConstructor_EmptyName() {
        Ingredient testIngredient = new Ingredient("test", "x");
        assertEquals("test", testIngredient.getName());
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testIngredientConstructor_EmptyName() {
//        String testRecipeName = new String();
//    }

}
