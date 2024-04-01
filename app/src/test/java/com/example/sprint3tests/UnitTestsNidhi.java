package com.example.sprint3tests;
import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.viewmodels.CookBookViewModel;

import org.junit.Assert;
import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * @author Nidhi Tarpara
 */
public class UnitTestsNidhi {
    @Test
    public void testIngredientQuantity() {
        String expectedQuantity = "2 cups";
        Ingredient ingredient = new Ingredient("Flour", expectedQuantity);
        String actualQuantity = ingredient.getQuantity();
        assertEquals("The quantity returned was not the expected one.", expectedQuantity, actualQuantity);
    }

    @Test
    public void testCheckWhiteSpace_WithWhiteSpace() {
        CookBookViewModel viewModel = new CookBookViewModel();
        String input = "test input";
        boolean result = viewModel.checkWhiteSpace(input);
        Assert.assertFalse("Expected false for input with whitespace", result);
    }

    @Test
    public void testCheckWhiteSpace_WithoutWhiteSpace() {
        CookBookViewModel viewModel = new CookBookViewModel();
        String input = "testInput";
        boolean result = viewModel.checkWhiteSpace(input);
        Assert.assertTrue("Expected true for input without whitespace", result);
    }
}

