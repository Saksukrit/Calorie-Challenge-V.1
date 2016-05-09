package com.example.kyowolf.caloriecalculator_v1.Diet2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.R;

import java.util.ArrayList;

import static android.app.AlertDialog.Builder;
import static android.app.AlertDialog.OnClickListener;

public class TotalDataActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener {
    ArrayList<itemOrder> itemQRCodeArrayLists = new ArrayList<>();
    ListView listView;
    FoodAdapter adapter;
    TextView total;
    Button login_btn;

    static int totalCal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_data);

        int sum = 0;
        Uri u =Uri.parse("content://total");
        Cursor c =getContentResolver().query(u, null, null, null, null);
        while (c.moveToNext()){
            itemQRCodeArrayLists.add(new itemOrder(c.getInt(1), c.getString(2), c.getString(3)));
            String arr[] = c.getString(3).split(" ");
            totalCal = Integer.valueOf(arr[0]);
            sum = totalCal + sum;

        }

        total = (TextView) findViewById(R.id.textView7);
        total.setText("Calorie total = "+ sum+ " Cals");

        adapter = new FoodAdapter(this, itemQRCodeArrayLists);
        listView = (ListView) findViewById(R.id.list1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        login_btn = (Button) findViewById(R.id.button6);
        login_btn.setOnClickListener(this);

    }
    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
        Builder adb=new Builder(TotalDataActivity.this);
        listView = (ListView) findViewById(R.id.list1);
        adb.setTitle("Select");
        final CharSequence[] items = { "Delete"};
        final int positionToRemove = position;
        adb.setItems(items, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                itemQRCodeArrayLists.remove(positionToRemove);
                Uri u = Uri.parse("content://total");
                Cursor c = getContentResolver().query(u, null, null, null, null);
                String ex = "_id =?";
                c.moveToPosition(position);
                String name = c.getString(c.getColumnIndex(FruitDatabase.COL_ID));
                String[] e = new String[]{String.valueOf(name)};
                int row = getContentResolver().delete(u, ex, e);
                ShowMS("Delete");

                listView.setAdapter(adapter);
            }
        });
        adb.show();
    }
    public void ShowMS (String ms){
        Toast.makeText(this, ms, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6:
                Intent intentProfile = new Intent(getApplicationContext(), Select_type_Order.class);
                startActivity(intentProfile);
                break;

            default:
                Toast.makeText(TotalDataActivity.this,
                        "not found",
                        Toast.LENGTH_LONG).show();
                break;

        }
    }
    public void goButtonClicked(View v) {
        TextView text = (TextView)findViewById(R.id.textView7);
        text.setVisibility(View.VISIBLE);
    }

}