package com.example.kyowolf.caloriecalculator_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by KyoWolf on 2/24/2016.
 */
public class ListOrder extends Activity {

    ListView listview;
    private static String TAG_orderfood = "orderfood";
    private static String TAG_name = "name";
    private static String TAG_type = "type";
    private static String TAG_calorie = "calorie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listorder);
        listview = (ListView) findViewById(R.id.list);


        final String[] order = new String[]{"ข้าวขาหมู     690", "เป็ดย่าง     400",
                "ก๋วยเตี๋ยวเรือ     320", "กุ้งอบวุ้นเส้น     300",
                "คะน้าหมูกรอบ     670", "ลูกเดือยต้มน้ำตาล     140",
                "ขนมจีน     50", "ข้าวแหนม     610", "ข้าวมันไก่     585",
                "ข้าวไข่เจียว     445"};


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
                Intent intenttotal = new Intent(getApplicationContext(), CalorieTotal.class);
                startActivity(intenttotal);
                
            }
        });
    }
}


