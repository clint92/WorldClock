package com.example.clint.ass2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FrontPageActivity <T>  extends AppCompatActivity {

    private String fileName = "savedInfo.bin";
    private EditText nameField;
    private EditText passwordField;
    private CheckBox remember;
    private CheckBox lock;
    private UserInfo userInfo;

    @Override
    /* This method control what happens on the creation of instance of the given class
    * it set the view to a specific xml layout*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage_clocktype);
        // Creating an instances of EditText and checkbox widgets. then assigning the widgets from the xml to them by using the ID
        nameField  = (EditText) findViewById(R.id.enterNameID);
        passwordField  = (EditText) findViewById(R.id.enterPassword);
        remember = (CheckBox) findViewById(R.id.checkBoxRemember);
        lock = (CheckBox) findViewById(R.id.checkBoxLock);

        // Use the loadSerFile to deserialize the UserInfo object.
        userInfo = loadSerFile(fileName);
        //if the object is not null. so an actual file with the object exist
        if(userInfo != null)
        {
            //if remember me check box is checked.
            if(userInfo.isRemember())
            {
                // set name and password field to the values from the UserInfo file. this will be the latest entered values.
                nameField.setText(userInfo.getUserName());
                passwordField.setText(userInfo.getPassword());
                remember.setChecked(userInfo.isRemember());
            }
            if(userInfo.isLock())
            {
                lock.setChecked(userInfo.isLock());
            }
        }
        else{
            userInfo = new UserInfo();
        }
    }

   /* @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putCharSequence(App.VSTUP, vstup.getText());
    }

    @Override
    public void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        vstup.setText(state.getCharSequence(App.VSTUP));
    }*/

   // This metod handles the activity after the button of frontpage_clocktype.xml is pressed
    public void goToTimeZoneActivity(View view)
    {

        //then extract the text written in the given EditText widgets.
        String name = nameField.getText().toString();
        String password = passwordField.getText().toString();
        TextView text = (TextView) findViewById(R.id.enterNameTextView);

        //if both fields are empty, then inform that in the textview.
        if(name.equals("") && password.equals(""))
        {

            text.setText(getString(R.string.no_Name_and_password_entered));
        }
        else if (name.equals(""))
        {
            // If the text is empty. Then set the text of the TextView of the same xml.
            text.setText(getString(R.string.no_Name_enered));
        }
        //if password fields is empty, then inform that in the textview.
        else if(password.equals(""))
        {
            text.setText(getString(R.string.no_password_enered));
        }
        //Calls the controlLogin method to see if if it maches the saved one, and only if if the lock is set to true.
        else if(userInfo.isLock() && !controlLogin(name,password))
        {
            text.setText(getString(R.string.wrong_login));
        }
        else
        {

            // Set the UserInfo object name and password, with the ones entered.
            userInfo.setUserName(name);
            userInfo.setPassword(password);

            // check if checkbox has been checked and if so save the info.
            //call method.
            checkForRememberMe();




            // If a name has ben written in the EditText widget go to new activity.
            // Creates a new intent with the next/ new activity.
            Intent myIntent = new Intent(view.getContext(),TimeZoneActivity.class);
            //creates a bundle, so the name entered can be transfered to the next activity.
            Bundle bundle = new Bundle();
            // Adding string to bundle
            bundle.putString("name",name);
            // Adding bundle to intent.
            myIntent.putExtras(bundle);
            //start the new activity.
            startActivity(myIntent);
            }

        }

        //Method used for deserialization
        public UserInfo loadSerFile(String savedInfo)
        {
            FileInputStream fis = null;
            ObjectInputStream in = null;
            try
            {
                // opens the file input stream to the file with the name given in parameter.
                fis = openFileInput(savedInfo);
                // Opens the object input stream. reads the object and typecast it to UserInfo object.
                in = new ObjectInputStream(fis);
                UserInfo ui = (UserInfo) in.readObject();
                fis.close();
                in.close();
                return ui;
            }
            //Print excception if it accours.
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return null;
        }

    //serialization part
    public void checkForRememberMe() {

        if (!remember.isChecked() && !lock.isChecked()) {
            // if both the checkboxs is not checked.
            // Get the file.
            File dir = getFilesDir();
            File f = new File(dir, fileName);
            // Delete the file so that no login or lock is remembered.
            boolean deleted = f.delete();
        }
        else
            {
                FileOutputStream fOut = null;
                ObjectOutputStream oOut = null;
                try {
                    // Opens the file output with a file name and sets the file mode to be private
                    // Creates the file if it do not exist
                    fOut = openFileOutput(fileName, Context.MODE_PRIVATE);
                    // creates the object output stream so that an object can be written to the file.
                    oOut = new ObjectOutputStream(fOut);

                    //if remember me box is checked set true
                    if (remember.isChecked()) {
                        userInfo.setRemember(true);
                    }
                    //If not set false
                    else
                    {
                        userInfo.setRemember(false);
                    }

                    //if lock box is checked set true
                    if(lock.isChecked())
                    {
                        userInfo.setLock(true);

                    }
                    //If not set false
                    else
                    {
                        userInfo.setLock(false);
                    }
                    // write to the file
                    oOut.writeObject(userInfo);
                    //Close the object output stream.
                    fOut.close();
                    oOut.close();
                }
                // print the exception if it accour.
                catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
    }

    //Method for compare login with the saved login.
    public boolean controlLogin(String name, String password)
    {
        //There is no saved login return true. then i can't be locked.
        if(userInfo.getUserName() == null && userInfo.getPassword() == null)
        {
            return true;
        }
        //if it maches return true
        else if(name.equals(userInfo.getUserName()) && password.equals(userInfo.getPassword()))
        {
            return true;
        }
        //If not return false
        else
        {
            return false;
        }
    }
}
