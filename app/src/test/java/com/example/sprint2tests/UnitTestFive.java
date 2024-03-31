package com.example.sprint2tests;

import static org.junit.Assert.assertEquals;

import android.widget.Button;

import com.google.errorprone.annotations.DoNotMock;
import com.model.Meal;
import com.model.User;
import com.viewmodels.UserViewModel;
import com.views.MealsFragment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 *
 * Testing that the the calculate calorie goal works for female as well
 * @author Jessica Kalloor
 */
public class UnitTestFive {
    @Test
    public void testUserCalculateCalorieGoalFemale() {
        User user = new User("test@example.com", "password");
        user.setHeight("64"); // 5 feet 4 inches
        user.setWeight("130"); // 130 pounds
        user.setIsMale(false); // Female
        assertEquals(1439, user.calculateCalorieGoal());
    }
}
