package com.example.sprint4tests;

import static org.junit.Assert.assertEquals;

import com.model.Ingredient;
import com.model.ShoppingItem;
import com.model.StrategySprint4.CookableRecipe;
import com.model.User;

import org.junit.Test;

import java.util.ArrayList;

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
    public void testOne() {
        String username = "nidhi.tarpara02@gmail.com";
        String password = "pass1234";
        User user = new User(username, password);
        ArrayList<ShoppingItem> shoppingList = new ArrayList<>();
        ShoppingItem item1 = new ShoppingItem("Oranges", "5", "150");
        ShoppingItem item2 = new ShoppingItem("oranges", "5", "100"); // Different calories, same name, and quantity
        ShoppingItem item3 = new ShoppingItem("Oranges", "10", "150"); // Different quantity
        shoppingList.add(item1);
        shoppingList.add(item2);
        shoppingList.add(item3);
        user.setShoppingList(shoppingList);
        boolean item12 = item1.equals(item2);
        boolean item13 = item1.equals(item3);
        assertEquals(true, item12);
        assertEquals(false, item13);
    }

    @Test
    public void testTwo() {
        String username = "nidhi.tarpara02@gmail.com";
        String password = "pass1234";
        User user = new User(username, password);
        ArrayList<Ingredient> pantry = new ArrayList<>();
        pantry.add(new Ingredient("Flour", "4"));
        pantry.add(new Ingredient("Sugar", "5"));
        user.setPantry(pantry);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour", "2")); // Requires 2 units of flour
        ingredients.add(new Ingredient("Sugar", "2")); // Requires 2 units of sugar
        CookableRecipe recipe = new CookableRecipe("Flour and Sugar Cake", ingredients);
        int flourDifference = Integer.parseInt(recipe.getIngredients().get(0).getQuantity())
                - Integer.parseInt(user.getPantry().get(0).getQuantity());
        int sugarDifference = Integer.parseInt(recipe.getIngredients().get(1).getQuantity())
                - Integer.parseInt(user.getPantry().get(1).getQuantity());
        boolean needToAddFlour = false;
        boolean needToAddSugar = false;
        if(flourDifference >= 1) {
            needToAddFlour = true;
        }
        if(sugarDifference >= 1) {
            needToAddSugar = true;
        }
        assertEquals(false, needToAddFlour);
        assertEquals(false, needToAddSugar);
    }
}

