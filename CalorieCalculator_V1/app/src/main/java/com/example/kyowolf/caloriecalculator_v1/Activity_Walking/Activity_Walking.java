package com.example.kyowolf.caloriecalculator_v1.Activity_Walking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.Activity_descript;
import com.example.kyowolf.caloriecalculator_v1.History.History;
import com.example.kyowolf.caloriecalculator_v1.Profile.Profile;
import com.example.kyowolf.caloriecalculator_v1.R;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class Activity_Walking extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button start, stop, note;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menubar, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemid_0:
                Toast.makeText(Activity_Walking.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                return true;
            case R.id.itemid_1:
                Toast.makeText(Activity_Walking.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                return true;
            case R.id.itemid_2:
                Toast.makeText(Activity_Walking.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(Activity_Walking.this,
                        "Setting",
                        Toast.LENGTH_LONG).show();

                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        note = (Button) findViewById(R.id.note);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        note.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.note:
                Intent note = new Intent(getApplicationContext(), Activity_descript.class);
                startActivity(note);
                finish();
                break;
            default:
                break;
        }
    }
}