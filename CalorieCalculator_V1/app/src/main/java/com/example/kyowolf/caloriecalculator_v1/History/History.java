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
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.Profile.Profile;
import com.example.kyowolf.caloriecalculator_v1.R;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class History extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button HisActivity, HisEating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        HisActivity = (Button) findViewById(R.id.HisActivity);
        HisEating = (Button) findViewById(R.id.HisEating);

        HisActivity.setOnClickListener(this);
        HisEating.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    }

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
                Toast.makeText(History.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                finish();
                return true;
            case R.id.itemid_1:
                Toast.makeText(History.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                finish();
                return true;
            case R.id.itemid_2:
                Toast.makeText(History.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(History.this,
                        "Setting",
                        Toast.LENGTH_LONG).show();

                return true;
            default:
                return false;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.HisActivity:
                Intent HisActivity = new Intent(getApplicationContext(), List_History_activity.class);
                startActivity(HisActivity);
                finish();
                break;
            case R.id.HisEating:
                Intent HisEating = new Intent(getApplicationContext(), List_History_Eating.class);
                startActivity(HisEating);
                finish();
                break;
            default:
                break;
        }
    }

}
