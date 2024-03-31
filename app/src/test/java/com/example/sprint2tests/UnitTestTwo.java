package com.example.sprint2tests;

import static org.junit.Assert.assertEquals;
import com.model.User;
import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * Test that User Model generates the correct userId
 * @author Will Akins
 */
public class UnitTestTwo {

    @Test
    public void testInsertNameHere() {
        String testUsername = "willhakins@gmail.com";
        String testPassword = "william";
        String expectedUserId = "willhakins";
        User testUser = new User(testUsername, testPassword);

        assertEquals(expectedUserId, testUser.getUserId());
    }
}
