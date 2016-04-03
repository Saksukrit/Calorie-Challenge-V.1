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

public class Select_type_Order extends AppCompatActivity implements View.OnClickListener {


    Button Food, Dessert, Fruit, Drink;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_type_order);

        Food = (Button) findViewById(R.id.Food);
        Dessert = (Button) findViewById(R.id.Dessert);
        Fruit = (Button) findViewById(R.id.Fruit);
        Drink = (Button) findViewById(R.id.Drink);

        Food.setOnClickListener(this);
        Dessert.setOnClickListener(this);
        Fruit.setOnClickListener(this);
        Drink.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
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
                Toast.makeText(Select_type_Order.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                finish();
                return true;
            case R.id.itemid_1:
                Toast.makeText(Select_type_Order.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                finish();
                return true;
            case R.id.itemid_2:
                Toast.makeText(Select_type_Order.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(Select_type_Order.this,
                        "Setting",
                        Toast.LENGTH_LONG).show();

                return true;
            default:
                return false;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Food:
                Intent intentF = new Intent(getApplicationContext(), ListOrder.class);
                startActivity(intentF);
                finish();
                break;
            case R.id.Dessert:
                Intent intentD = new Intent(getApplicationContext(), ListOrder.class);
                startActivity(intentD);
                finish();
                break;
            case R.id.Fruit:
                Intent intentFr = new Intent(getApplicationContext(), ListOrder.class);
                startActivity(intentFr);
                finish();
                break;
            case R.id.Drink:
                Intent intentDr = new Intent(getApplicationContext(), ListOrder.class);
                startActivity(intentDr);
                finish();
                break;

        }

    }


}
