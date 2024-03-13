package com.example;

import com.model.Meal;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * testing if illegal argument triggers when non-integer is inputted
 * @author Christine Lee
 */
public class UnitTestTen {

    @Test (expected = IllegalArgumentException.class)
    public void testNonInteger() {
        Meal meal = new Meal("Pizza", 'c');
    }

}
