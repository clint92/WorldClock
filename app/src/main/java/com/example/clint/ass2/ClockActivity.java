package com.example.clint.ass2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;


/**
 * Created by clint on 30-07-2017.
 */

public class ClockActivity extends AppCompatActivity {
    String timeZone;

    public ClockActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
        {
            setTheme(R.style.AppTheme);
            // On creation change the layout to clock.xml
            super.onCreate(savedInstanceState);
            setContentView(R.layout.clock);
            // extract the bundel.
            Bundle bundle = getIntent().getExtras();
            //extract the string with chosen time zone.
            timeZone = bundle.getString("timezone");
            // Set the time zone with use of the setTimeZone method.
            setTimezone(timeZone);
        }

    // Method that takes you back to previous activity. Used when button is pressed.
    public void goBackToTimeZoneActivity(View view)
    {
        finish();
    }

    // Method used to set the time zone on xml
    public void setTimezone(String timezone)
    {
        // Create an instance and assign the TextView from the xml
        TextView text = (TextView) findViewById(R.id.choosenTimeZone);
        // Set the text to the string recieved in the bundle.
        text.setText(timezone);
        // Create an instance of TextClock and assign the TextClock from the xml
        TextClock clock = (TextClock) findViewById(R.id.timeZoneTime);

        // If statement that check which time zone is chosen and set the time of the clock to the correct time zone.

        if(timezone.equals("Paris"))
        {
            clock.setTimeZone("Europe/Paris");
        }
        else if(timezone.equals("New York"))
        {
            clock.setTimeZone("America/New_York");
        }
        else if(timezone.equals("Copenhagen"))
        {
            clock.setTimeZone("Europe/Copenhagen");
        }
        else if(timezone.equals("Tokyo"))
        {
            clock.setTimeZone("Asia/Tokyo");
        }
        else if(timezone.equals("Perth"))
        {
            clock.setTimeZone("Australia/Perth");
        }
        else if(timezone.equals("Bankok"))
        {
            clock.setTimeZone("Asia/Bangkok");
        }
        else if(timezone.equals("London"))
        {
            clock.setTimeZone("Europe/London");
        }
        else if(timezone.equals("Rio"))
        {
            clock.setTimeZone("America/Rio_Branco");
        }

    }
}

