package com.example.kyowolf.caloriecalculator_v1.Diet2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.R;

import java.util.ArrayList;

import static android.app.AlertDialog.Builder;
import static android.app.AlertDialog.OnClickListener;

public class DessertListActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    ArrayList<itemOrder> itemQRCodeArrayLists = new ArrayList<>();
    ListView listView;
    FoodAdapter adapter;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_list);

        Uri u =Uri.parse("content://Dessert");
        Cursor c =getContentResolver().query(u, null, null, null, null);
        while (c.moveToNext()){
            itemQRCodeArrayLists.add(new itemOrder(c.getInt(1), c.getString(2), c.getString(3)));
        }

        adapter = new FoodAdapter(this, itemQRCodeArrayLists);
        listView = (ListView) findViewById(R.id.list1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);



        login_btn = (Button) findViewById(R.id.button3);
        login_btn.setOnClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Builder adb=new Builder(DessertListActivity.this);
        listView = (ListView) findViewById(R.id.list1);
        adb.setTitle("Select");
        final CharSequence[] items = { "Delete","Add Menu"};
        final int positionToRemove = position;
        adb.setItems(items, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(items[which].equals("Delete")) {
                    itemQRCodeArrayLists.remove(positionToRemove);
                    Uri u = Uri.parse("content://Dessert");
                    Cursor c = getContentResolver().query(u, null, null, null, null);
                    String ex = "_id =?";
                    c.moveToPosition(position);
                    String name = c.getString(c.getColumnIndex(DessertDatabase.COL_ID));
                    String[] e = new String[]{String.valueOf(name)};
                    int row = getContentResolver().delete(u, ex, e);
                    ShowMS("Delete");
                    listView.setAdapter(adapter);
                }else if(items[which].equals("Add Menu")){
                    itemOrder d = adapter.getItem(position);
                    Uri u =Uri.parse("content://total");
                    ContentValues cv = new ContentValues();
                    cv.put("icon",d.getIcon());
                    cv.put("productname", d.getType());
                    cv.put("timeDate", d.getName());
                    Uri nari = getContentResolver().insert(u, cv);
                    Intent intent = new Intent(getApplicationContext(), TotalDataActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        adb.show();
    }
    public void ShowMS (String ms){
        Toast.makeText(this, ms, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Intent intentProfile = new Intent(getApplicationContext(), Select_type_Order.class);
                startActivity(intentProfile);
                break;

            default:
                Toast.makeText(DessertListActivity.this,
                        "not found",
                        Toast.LENGTH_LONG).show();
                break;

        }
    }

}