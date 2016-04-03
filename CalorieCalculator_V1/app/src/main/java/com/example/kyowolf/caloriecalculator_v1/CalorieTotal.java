package com.example.kyowolf.caloriecalculator_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class CalorieTotal extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button back, save;
    ListView listview;

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
                Toast.makeText(CalorieTotal.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), CalorieTotal.class);
                startActivity(intentProfile);
                return true;
            case R.id.itemid_1:
                Toast.makeText(CalorieTotal.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                return true;
            case R.id.itemid_2:
                Toast.makeText(CalorieTotal.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(CalorieTotal.this,
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
        setContentView(R.layout.calorie_total);

        back = (Button) findViewById(R.id.back);
        save = (Button) findViewById(R.id.save);

        back.setOnClickListener(this);
        save.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        listview = (ListView) findViewById(R.id.selectedlist);


        final String[] order = new String[]{"กุ้งอบวุ้นเส้น     300", "ข้าวขาหมู     690", "คะน้าหมูกรอบ     670", "ลูกเดือยต้มน้ำตาล     140"};


        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < order.length; ++i) {
            list.add(order[i]);
        }


        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent back = new Intent(getApplicationContext(), ListOrder.class);
                startActivity(back);
                finish();
                break;
            case R.id.save:
                Intent save = new Intent(getApplicationContext(), Profile.class);
                startActivity(save);
                Toast.makeText(CalorieTotal.this,
                        "saved",
                        Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                break;
        }
    }
}
