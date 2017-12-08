package com.example.clint.ass2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by clint on 31-07-2017.
 */

public class TimeZoneActivity extends AppCompatActivity {

    String name;
    String PREFS_NAME = "AOP_PREFS";
    String PREFS_KEY = "";

    public TimeZoneActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(R.style.AppTheme);

        // What to do on the creation of this activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_zone);
        //extracting the bundle that came with the intent.
        Bundle bundle = getIntent().getExtras();
        // extracting the string from that bundle.
        name = bundle.getString("name");
        PREFS_KEY = name;
        // Get the textview from the new xml by using the ID.
        TextView hi = (TextView) findViewById(R.id.nameTextView);
        // Set the text of that TextView with the name extracted from the bundle.
        hi.setText(getString(R.string.welcom_text_with_name) + " " + name);

        RadioGroup timezones = (RadioGroup) findViewById(R.id.timeZoneRadioGroup);

        //Get the shared preference file.
        final String text;
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //extract the string with the given key.
        text = settings.getString(PREFS_KEY, null);

        if(text != null)
        {
            //If the value is not null then parse the string to an int.
            int textAsInt = Integer.parseInt(text);
            //And check the radiobutton in timezones radioButtonGroup with that value.
            timezones.check(textAsInt);
        }

    }
    // method used on the press of the button in this xml.
    public void goToClockActivity(View view)
    {

        // Create new intent. refering to the next activity, ClockActivity.
        Intent myIntent = new Intent(view.getContext(),ClockActivity.class);
        //Creates a bundle for the transfer of the chosen timezone.
        Bundle bundle = new Bundle();
        // Adding the string to the bundle.
        bundle.putString("timezone",checkForChoosenTimeZone());
        // Adding bundle to the intent.
        myIntent.putExtras(bundle);
        //Start new activity
        startActivity(myIntent);

    }

    // This method is used to determined the chosen timezone in the RadioGroup.
    public String checkForChoosenTimeZone()
    {
        //Happens when the button is clicked

        // Create an instance of RadioGroup. Add the values from the one in the xml.
        RadioGroup timezones = (RadioGroup) findViewById(R.id.timeZoneRadioGroup);
        // ekstract the ID of the checked RadioButton
        int timezoneID = timezones.getCheckedRadioButtonId();
        // Create the String which are to be returned.
        String city;

        // creates a shared preference file with a given name and with private access.
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //Creates an editor so it is possible to write to the file.
        SharedPreferences.Editor editor = settings.edit();

        // Writes the key / value pair to the shared preference file.
        editor.putString(PREFS_KEY, String.valueOf(timezoneID));
        editor.commit();  //maybe apply


        // If non of the RadioButton are checked
        if (timezones.getCheckedRadioButtonId() == -1)
        {
            // Giv city value.
            city = getString(R.string.noTimeZoneChoosenText);
        }
        // If any of the RadioButtons are checked.
        else
        {
            //Find the RadioButton
            RadioButton button = (RadioButton) findViewById(timezoneID);
            //Extract the text from the RadioButton and assign it to city
            city = (String) button.getText();


        }
        return city;
    }
}
