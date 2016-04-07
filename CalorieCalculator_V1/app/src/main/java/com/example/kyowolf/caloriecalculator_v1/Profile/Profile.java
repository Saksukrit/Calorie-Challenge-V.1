package com.example.kyowolf.caloriecalculator_v1.Profile;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.Activity_Running;
import com.example.kyowolf.caloriecalculator_v1.Activity_Walking.Activity_Walking;
import com.example.kyowolf.caloriecalculator_v1.History.History;
import com.example.kyowolf.caloriecalculator_v1.LoginMainActivity;
import com.example.kyowolf.caloriecalculator_v1.R;
import com.example.kyowolf.caloriecalculator_v1.Diet.Select_type_Order;
import com.example.kyowolf.caloriecalculator_v1.StepCounter.StartActivity;
import com.example.kyowolf.caloriecalculator_v1.StepCounter.StepCounterActivity;
import com.example.kyowolf.caloriecalculator_v1.StepCounter.StepCounterService;
import com.example.kyowolf.caloriecalculator_v1.StepCounter.StepDetector;


/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class Profile extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button bio, running, walking, eating, training;
    TextView nameprofile, age, height, weight, bmr, tdee;

    SQLiteDatabase db;

    int mid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        bio = (Button) findViewById(R.id.bio);
        running = (Button) findViewById(R.id.running);
        walking = (Button) findViewById(R.id.walking);
        eating = (Button) findViewById(R.id.eating);
        training = (Button) findViewById(R.id.training);

        bio.setOnClickListener(this);
        running.setOnClickListener(this);
        walking.setOnClickListener(this);
        eating.setOnClickListener(this);
        training.setOnClickListener(this);

        nameprofile = (TextView) findViewById(R.id.nameprofile);
        age = (TextView) findViewById(R.id.age);
        height = (TextView) findViewById(R.id.height);
        weight = (TextView) findViewById(R.id.weight);
        bmr = (TextView) findViewById(R.id.bmr);
        tdee = (TextView) findViewById(R.id.tdee);


        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        DB_profile db_profile = new DB_profile(this);
        db_profile.getWritableDatabase();


        /* test insert !!!
        long flg1 = db_profile.InsertData(1, "Krit", "krit", "123456", "male", 174, 77, 1848, 2218, 21);
        if (flg1 > 0) {
            Toast.makeText(Profile.this, "Insert(1) Data Successfully",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Profile.this, "Insert(1) Data Failed.",
                    Toast.LENGTH_LONG).show();
        }
        */


        if (mid == 0) {
            mid = getIntent().getExtras().getInt("mid");
        }


        // select data !!!

        String arrData[] = db_profile.SelectData(mid);
        if (arrData == null) {
            Toast.makeText(Profile.this, "Not found Data!",
                    Toast.LENGTH_LONG).show();
        } else {
            nameprofile.setText(arrData[1]);
            age.setText(arrData[9]);
            height.setText(arrData[5]);
            weight.setText(arrData[6]);
            bmr.setText(arrData[7]);
            tdee.setText(arrData[8]);
        }


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
                Toast.makeText(Profile.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                return true;
            case R.id.itemid_1:
                Toast.makeText(Profile.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                return true;
            case R.id.itemid_2:
                Toast.makeText(Profile.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(Profile.this,
                        "Setting",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_4:
                Toast.makeText(Profile.this,
                        "Logout",
                        Toast.LENGTH_LONG).show();
                Intent intentlogin = new Intent(getApplicationContext(), LoginMainActivity.class);
                startActivity(intentlogin);
                finish();
                return true;
            default:
                return false;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bio:
                Intent intentProfile = new Intent(getApplicationContext(), Profile_Bio.class);
                intentProfile.putExtra("midbio", mid);
                startActivity(intentProfile);
                finish();
                break;
            case R.id.running:
                Intent intentProfile1 = new Intent(getApplicationContext(), StepCounterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("run", false);
                intentProfile1.putExtras(bundle);
                startActivity(intentProfile1);

                break;
            case R.id.walking:
                Intent intentProfile2 = new Intent(getApplicationContext(), Activity_Walking.class);
                startActivity(intentProfile2);
                break;
            case R.id.eating:
                Intent intentProfile3 = new Intent(getApplicationContext(), Select_type_Order.class);
                startActivity(intentProfile3);
                break;
            case R.id.training:

                break;
            default:
                Toast.makeText(Profile.this,
                        "not found",
                        Toast.LENGTH_LONG).show();
                break;

        }
    }
}
