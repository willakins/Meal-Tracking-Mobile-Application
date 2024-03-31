package com.example.sprint2tests;
import static org.junit.Assert.assertEquals;
import com.model.User;
import org.junit.Test;

/**
 * File placeholder for testing
 * do not start writing the test until you change author name and do a pull request.
 * additionally, don't start writing your pull request until you pull from main
 * so that you don't cause a merge conflict
 * @author Nidhi Tarpara
 */
public class UnitTestSeven {
    @Test
    public void testAddCalories() {
        User user = new User("testUser@example.com", "password");
        user.addCalories(500);
        assertEquals(500, user.getCaloriesToday());
    }
}
