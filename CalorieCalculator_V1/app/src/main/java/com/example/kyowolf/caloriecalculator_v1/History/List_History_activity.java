package com.example.kyowolf.caloriecalculator_v1.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.R;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class List_History_activity extends AppCompatActivity {
    ActionBar actionBar;
    private ListView listview;

    private ActivityAdapter activityAdapter;
    private DB_ActivityHelper db_activityHelper;
    private static final int ENTER_DATA_REQUEST_CODE = 1;

    private static final String TAG = List_History_activity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_history_activity);

        db_activityHelper = new DB_ActivityHelper(this);

        String[] count = db_activityHelper.getCountRow();
        //String[][] all = db_activityHelper.SelectAllDataForList(Integer.parseInt(count[0]));
        //String[][] all = db_activityHelper.SelectAllDataForList(3);

        int[] id = {R.drawable.run, R.drawable.walk, R.drawable.walk, R.drawable.run, R.drawable.walk, R.drawable.run, R.drawable.walk, R.drawable.walk, R.drawable.run, R.drawable.walk};


        String[] arr = db_activityHelper.SelectNewData();


        String[] topic = {arr[9] + "   " + arr[7] + "   " + arr[8] + "          " + arr[5] + " cal."
                , "walking   9/5/2016   15:33          50.65 cal."
                , "walking   9/5/2016   11:16          51.43 cal."
                , "running   8/5/2016   17:53          80.16 cal."
                , "walking   8/5/2016   13:11          46.59 cal."
                , "running   7/5/2016   20:21          76.22 cal."
                , "walking   7/5/2016   9:42          21.36 cal."
                , "walking   7/5/2016   13:03          30.49 cal."
                , "running   6/5/2016   16:49          64.16 cal."
                , "walking   6/5/2016   12:06          32.20 cal."};


//String f = all[1][1].toString();                          //all[][]   invalid from method SelectAllDataForList()
//String[] arr = new String[3];
        /*
        for (int i = 0; i < 3; i++) {
            String[] arr = db_activityHelper.SelectByID(String.valueOf(15));
            topic[i] = arr[8];
        }
        */

        ActivityAdapter adapter = new ActivityAdapter(getApplicationContext(), topic, id);


        listview = (ListView) findViewById(R.id.listHisActivity);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "clicked on item: " + position);


                Toast.makeText(List_History_activity.this,
                        "test ",
                        Toast.LENGTH_LONG).show();

                Intent intentHistory = new Intent(getApplicationContext(), History_activity.class);
                intentHistory.putExtra("date", "fsd");
                startActivity(intentHistory);
                finish();
            }
        });


    }


}