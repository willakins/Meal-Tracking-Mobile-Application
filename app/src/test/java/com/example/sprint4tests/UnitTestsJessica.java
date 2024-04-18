package com.example.sprint4tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import com.model.Ingredient;
import com.viewmodels.PantryViewModel;

import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * @author Jessica Kalloor
 */
public class UnitTestsJessica {
    @Test
    public void testIngredientQuantity() {
        Ingredient ingredient = new Ingredient("Chicken", "2");
        assertEquals(2, Integer.parseInt(ingredient.getQuantity()));
    }

    @Test
    public void testIngredientWhiteSpaceAndNull() {
        Ingredient ingredient = new Ingredient("   ", null);
        boolean hasWhiteSpace = !ingredient.getName().trim().isEmpty();
        boolean isNotNull = ingredient.getQuantity() != null;

        assertEquals(false, hasWhiteSpace);
        assertEquals(false, isNotNull);
    }
}
