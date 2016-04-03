package com.example.kyowolf.caloriecalculator_v1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
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