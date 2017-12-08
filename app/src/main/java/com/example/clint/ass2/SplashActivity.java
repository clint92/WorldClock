package com.example.clint.ass2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //create a variale defining the duration of the splash screen
    private final int splashDuration = 1000;

    // note this way of creating a splash screen is with a fixed time. So not just he start up time.
    // I choose to do this because the start up time is to fast to see the splash screen.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_splash1);

        // create a new Handler which will start the frontPageActivity
        // and close the splash screen after the time defined above.
       new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Creates the intent which begins the next activity. FrontPageActivity
                /* Create an Intent that will start the Menu-Activity. */
                Intent frontPage = new Intent(SplashActivity.this,FrontPageActivity.class);
                SplashActivity.this.startActivity(frontPage);
                SplashActivity.this.finish();
            }
        }, splashDuration);
    }
}
