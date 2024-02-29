package com.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //This is the splash page
    // Just display an initial visual of some kind, then instantly move to LoginActivity
    private Thread timerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Thread timerThread;
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        timerThread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timerThread.start();
    }
}
