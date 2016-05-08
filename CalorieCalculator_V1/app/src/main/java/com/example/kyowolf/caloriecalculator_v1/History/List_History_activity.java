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
        String[][] all = db_activityHelper.SelectAllDataForList(3);

        int[] id = {R.drawable.run, R.drawable.walk, R.drawable.walk};

        String[] topic = new String[3];
        for (int i = 0; i < 3; i++) {
            topic[i] = "jopkp";
        }


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