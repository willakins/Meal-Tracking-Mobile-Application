package com.example.sprint4tests;

import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.model.ShoppingItem;
import com.model.User;
import com.viewmodels.PantryViewModel;

import org.junit.Test;

import java.util.ArrayList;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * @Christine Lee
 */
public class UnitTestsChristine {
    @Test
    public void testRemoveItem() {
        /**
         * adding the items
         */
        String testUsername = "unis4christine@gmail.com";
        String testPassword = "password";
        User testUser = new User(testUsername, testPassword);
        ArrayList<ShoppingItem> shoppingList = new ArrayList<>();
        ShoppingItem item1 = new ShoppingItem("Pasta", "2", "20");
        ShoppingItem item2 = new ShoppingItem("Cheese", "3", "80");
        ShoppingItem item3 = new ShoppingItem("Chicken", "4", "100");
        ShoppingItem item4 = new ShoppingItem("Butter", "1", "30");
        shoppingList.add(item1);
        shoppingList.add(item2);
        shoppingList.add(item3);
        shoppingList.add(item4);

        /**
         * testing size
         */
        testUser.setShoppingList(shoppingList);
        assertEquals(4, testUser.getShoppingList().size());

        /**
         * removing item
         */
        shoppingList.remove(item3);
        testUser.setShoppingList(shoppingList);
        assertEquals(3, testUser.getShoppingList().size());

    }

    @Test
    public void testDuplicateItem() {

        String testUsername = "unis4christine@gmail.com";
        String testPassword = "password";
        User testUser = new User(testUsername, testPassword);

        ArrayList<ShoppingItem> shoppingList = new ArrayList<>();
        ShoppingItem item1 = new ShoppingItem("Pasta", "2", "20");
        ShoppingItem item2 = new ShoppingItem("Cheese", "3", "80");
        ShoppingItem item3 = new ShoppingItem("Chicken", "4", "100");
        ShoppingItem item4 = new ShoppingItem("Butter", "1", "30");

        shoppingList.add(item1);
        shoppingList.add(item1);
        shoppingList.add(item2);
        shoppingList.add(item3);
        shoppingList.add(item4);

        testUser.setShoppingList(shoppingList);

        assertEquals(0, testUser.findShoppingItem(item1));
        assertEquals(2, testUser.findShoppingItem(item2));
        assertEquals(3, testUser.findShoppingItem(item3));
        assertEquals(4, testUser.findShoppingItem(item4));

    }

}
