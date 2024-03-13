package com.example;

import static org.junit.Assert.assertEquals;

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
    /* This test is to check if the personal info (specifically height
    and weight) updates correctly when modified in the Personal Info
    page.
     */
    @Test
    public void testHeightAndWeightUpdate() {
        String testHeight = "72";
        String testWeight = "220";
        User testUser = new User();
        testUser.setHeight(testHeight);
        testUser.setWeight(testWeight);

        assertEquals(testHeight, testUser.getHeight());
        assertEquals(testWeight, testUser.getWeight());
    }
}
