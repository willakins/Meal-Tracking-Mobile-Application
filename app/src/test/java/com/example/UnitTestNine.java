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
public class UnitTestNine {

    /* For test 9, I am writing a test that will check if updating
    the gender boolean on the Personal Info page will update the
    gender displayed on the Input Meals page as this did not work
    for me when I originally tried it.
     */
    @Test
    public void testGenderModification() {
        boolean testFemale = false; // isMale is false
        boolean testMale = true; //isMale is true
        User testUser = new User();

        testUser.setIsMale(false);
        assertEquals(testFemale, testUser.getIsMale());

        testUser.setIsMale(true);
        assertEquals(testMale, testUser.getIsMale());
    }
}
