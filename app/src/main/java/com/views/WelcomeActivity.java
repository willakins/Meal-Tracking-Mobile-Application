package com.views;

public class WelcomeActivity {
    //This is the splash page
    // Just display an initial visual of some kind, then instantly move to LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViews(R.layout.welcome_page);
    }
}
