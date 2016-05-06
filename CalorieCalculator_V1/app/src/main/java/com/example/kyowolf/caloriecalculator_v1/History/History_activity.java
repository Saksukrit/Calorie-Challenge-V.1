package com.example.kyowolf.caloriecalculator_v1.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.Activity_Walking.DB_Activity;
import com.example.kyowolf.caloriecalculator_v1.Profile.DB_profile;
import com.example.kyowolf.caloriecalculator_v1.Profile.Profile;
import com.example.kyowolf.caloriecalculator_v1.R;

import java.text.DecimalFormat;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class History_activity extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button delete;

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
                Toast.makeText(History_activity.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                finish();
                return true;
            case R.id.itemid_1:
                Toast.makeText(History_activity.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                finish();
                return true;
            case R.id.itemid_2:
                Toast.makeText(History_activity.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(History_activity.this,
                        "Setting",
                        Toast.LENGTH_LONG).show();

                return true;
            default:
                return false;
        }
    }

    private TextView calorie, distance, duration, typeActivity, speed, step, date;
    private String dateBy = null;

    DB_Activity db_activity = new DB_Activity(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        calorie = (TextView) findViewById(R.id.calories);
        distance = (TextView) findViewById(R.id.distances);
        duration = (TextView) findViewById(R.id.durations);
        typeActivity = (TextView) findViewById(R.id.AcName);
        speed = (TextView) findViewById(R.id.speeds);
        step = (TextView) findViewById(R.id.step);
        date = (TextView) findViewById(R.id.date);

        if (dateBy == null) {
            dateBy = getIntent().getExtras().getString("date");
        }

        String arrData[] = db_activity.SelectDataByDate(dateBy);
        if (arrData == null) {
            Toast.makeText(History_activity.this, "Not found Data!",
                    Toast.LENGTH_LONG).show();
        } else {
            calorie.setText(arrData[5]);
            distance.setText(arrData[3]);
            duration.setText(arrData[2]);
            typeActivity.setText(arrData[8]);
            speed.setText(arrData[4]);
            step.setText(arrData[6]);
            date.setText(arrData[7]);
        }


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                Toast.makeText(History_activity.this,
                        "delete ****",
                        Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}