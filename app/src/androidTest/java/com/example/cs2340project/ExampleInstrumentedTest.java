package com.example.cs2340project;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.os.Looper;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.auth.FirebaseAuth;
import com.viewmodels.LoginViewModel;
import com.views.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.cs2340project", appContext.getPackageName());
    }

    /**
     * Couldn't figure out how to test this without getting errors I give up
     */
    @Test
    public void InvalidInputInLogin() {
        LoginViewModel testModel = LoginViewModel.getInstance();
        LoginActivity la = new LoginActivity();
        FirebaseAuth testAuth = FirebaseAuth.getInstance();

        String testUsername = "willhakins@gmail.com";
        String testPassword = "william";
        String testNullUsername = "";
        String testNullPassword = "";
        String testWhiteSpaceUsername = "will hakins@gmail.com";
        String testWhiteSpacePassword = "will iam";

        //Test that there is no logged in user first
        assertEquals(null, testModel.getUser());


        //Test that there is still no user when null strings are passed
        testModel.login(la, testAuth, testNullUsername, testPassword);
        assertEquals(null, testModel.getUser());

        testModel.login(la, testAuth, testUsername, testNullPassword);
        assertEquals(null, testModel.getUser());

        testModel.login(la, testAuth, testNullUsername, testNullPassword);
        assertEquals(null, testModel.getUser());


        //Test that there is still no user when strings with whitespace are passed
        testModel.login(la, testAuth, testWhiteSpaceUsername, testPassword);
        assertEquals(null, testModel.getUser());

        testModel.login(la, testAuth, testUsername, testWhiteSpacePassword);
        assertEquals(null, testModel.getUser());

        testModel.login(la, testAuth, testWhiteSpaceUsername, testWhiteSpacePassword);
        assertEquals(null, testModel.getUser());


        //Test that the correct user is finally logged in with correct input
        testModel.login(la, testAuth, testUsername, testPassword);
        assertEquals("willhakins@gmail.com", testModel.getUser().getUsername());
    }
}