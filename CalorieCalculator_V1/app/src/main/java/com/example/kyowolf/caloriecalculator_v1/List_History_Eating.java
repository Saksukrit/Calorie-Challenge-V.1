package com.example.kyowolf.caloriecalculator_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class List_History_Eating extends AppCompatActivity {
    ActionBar actionBar;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_history_eating);

        listview = (ListView) findViewById(R.id.listHisEating);


        final String[] order = new String[]{"2100  calorie       20/12/2015", "1800  calorie       23/16/2015", "1950  calorie       24/16/2015"};


        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < order.length; ++i) {
            list.add(order[i]);
        }


        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Select : " + order[((int) id)].toString(), Toast.LENGTH_LONG)
                        .show();
                Intent intenttotal = new Intent(getApplicationContext(), History_Eating.class);
                startActivity(intenttotal);
                finish();
            }
        });
    }
}