package com.example.xander.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText mInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = (EditText) findViewById(R.id.input_field);
        
    }

    public void doDial(View view) {
        String inputString = mInputField.getText().toString();
        if (!inputString.equals("") && inputString.matches("[0-9\\+]{3,20}")){
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + inputString));
            startActivity(callIntent);
        } else {
            Toast.makeText(this, "Invalid Telephone number!", Toast.LENGTH_SHORT).show();
        }
    }

    public void doBrowse(View view) {
        String inputString = mInputField.getText().toString();
        if (!inputString.equals("") && inputString.matches("([\\da-z\\.-]+)\\.([a-z\\.]{2,6})")){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse("https://" + inputString));
            startActivity(browserIntent);
        } else {
            Toast.makeText(this, "Invalid web site!", Toast.LENGTH_SHORT).show();
        }
    }

    public void setAlarm(View view) {
        String inputString = mInputField.getText().toString();
        if (!inputString.equals("") && inputString.matches("[0-9][0-9]:[0-9][0-9]")){
            int hours = Integer.parseInt(inputString.split(":")[0]);
            int min = Integer.parseInt(inputString.split(":")[1]);
            Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
            alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Ninja Alarm");
            alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hours);
            alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, min);
            startActivity(alarmIntent);
        } else {
            Toast.makeText(this, "Invalid Alarm time!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
