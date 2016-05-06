package com.example.kyowolf.caloriecalculator_v1.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.R;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class List_History_activity extends AppCompatActivity {
    ActionBar actionBar;
    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_history_activity);


        listview = (ListView) findViewById(R.id.listHisActivity);


        int[] resId = {R.drawable.run, R.drawable.walk};

        String[] list = {"Running", "Walking"};

        ActivityAdapter adapter = new ActivityAdapter(getApplicationContext(), list, resId);


        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {                     /////AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {


                Toast.makeText(List_History_activity.this,
                        "test",
                        Toast.LENGTH_LONG).show();

                Intent intentHistory = new Intent(getApplicationContext(), History_activity.class);
                intentHistory.putExtra("date","fsd");
                startActivity(intentHistory);
                finish();


            }
        });
    }
}