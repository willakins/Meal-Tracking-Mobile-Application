package com.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    //This is the splash page
    // Just display an initial visual of some kind, then instantly move to LoginActivity
    private Thread timerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        timerThread = new Thread() {
            public void run() {
                boolean timerRunning = true;
                while(timerRunning) {
                    try {
                        Thread.sleep(1000);
                        startActivity(intent);
                        timerRunning = false;
                    } catch (InterruptedException e) {
                        timerRunning = false;
                        throw new RuntimeException(e);
                    }
                }

            }
        };
        timerThread.start();
    }
}
