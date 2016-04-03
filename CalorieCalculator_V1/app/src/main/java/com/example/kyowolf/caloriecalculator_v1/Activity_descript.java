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
public class Activity_descript extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button addimage, cancel, ok;

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
                Toast.makeText(Activity_descript.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                return true;
            case R.id.itemid_1:
                Toast.makeText(Activity_descript.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                return true;
            case R.id.itemid_2:
                Toast.makeText(Activity_descript.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(Activity_descript.this,
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
        setContentView(R.layout.activity_descript);

        addimage = (Button) findViewById(R.id.addimage);
        cancel = (Button) findViewById(R.id.cancel);
        ok = (Button) findViewById(R.id.ok);

        addimage.setOnClickListener(this);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                Intent cancel = new Intent(getApplicationContext(), Profile.class);
                startActivity(cancel);
                Toast.makeText(Activity_descript.this,
                        "OK Activity",
                        Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.ok:
                Intent ok = new Intent(getApplicationContext(), Profile.class);
                startActivity(ok);
                finish();
                break;
            case R.id.addimage:
                Toast.makeText(Activity_descript.this,
                        "Add Image ^^",
                        Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
