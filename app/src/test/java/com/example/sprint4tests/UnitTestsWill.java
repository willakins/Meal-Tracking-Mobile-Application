package com.example.sprint4tests;

import static org.junit.Assert.assertEquals;

import com.model.ShoppingItem;
import com.model.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 * Tests if the PantryViewModel does correct null and whitespace checks for data
 *
 * @author Will Akins
 */
public class UnitTestsWill {
    @Test
    public void testEqualsShoppingItem() {
        ShoppingItem item1 = new ShoppingItem("Carrot", "4", "30");
        ShoppingItem item2 = new ShoppingItem("carrot", "4", "30");
        ShoppingItem item3 = new ShoppingItem("Carrot", "5", "30");
        ShoppingItem item4 = new ShoppingItem("Carrot", "4", "60");
        assertEquals(true, item1.equals(item2));
        assertEquals(true, item1.equals(item4));
        assertEquals(false, item1.equals(item3));
        item3.setQuantity("4");
        assertEquals(true, item1.equals(item3));
    }

    @Test
    public void testFindItem() {
        String testUsername = "willhakins@gmail.com";
        String testPassword = "password";
        User testUser = new User(testUsername, testPassword);
        ArrayList<ShoppingItem> shoppingList = new ArrayList<>();
        ShoppingItem item1 = new ShoppingItem("Carrots", "2", "30");
        ShoppingItem item2 = new ShoppingItem("Apples", "3", "40");
        ShoppingItem item3 = new ShoppingItem("Chicken", "8", "200");
        ShoppingItem item4 = new ShoppingItem("Bread", "1", "120");
        ShoppingItem item5 = new ShoppingItem("Cheese", "6", "90");
        shoppingList.add(item1);
        shoppingList.add(item2);
        shoppingList.add(item3);
        shoppingList.add(item4);
        shoppingList.add(item5);
        ShoppingItem notListed = new ShoppingItem("Watermelon", "1", "20");
        testUser.setShoppingList(shoppingList);
        assertEquals(0, testUser.findShoppingItem(item1));
        assertEquals(1, testUser.findShoppingItem(item2));
        assertEquals(-1, testUser.findShoppingItem(notListed));

    }
}
