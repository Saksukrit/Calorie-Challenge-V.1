package com.example.kyowolf.caloriecalculator_v1.Profile;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyowolf.caloriecalculator_v1.History.History;
import com.example.kyowolf.caloriecalculator_v1.R;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class Profile_Bio extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button cancel, save;
    TextView height, weight, age;
    RadioButton male, female;

    int mid;
    double bmr, tdee;
    String gender;

    SQLiteDatabase db;

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
                Toast.makeText(Profile_Bio.this,
                        "Profile",
                        Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(intentProfile);
                return true;
            case R.id.itemid_1:
                Toast.makeText(Profile_Bio.this,
                        "History",
                        Toast.LENGTH_LONG).show();
                Intent intentHistory = new Intent(getApplicationContext(), History.class);
                startActivity(intentHistory);
                return true;
            case R.id.itemid_2:
                Toast.makeText(Profile_Bio.this,
                        "Challenge",
                        Toast.LENGTH_LONG).show();

                return true;
            case R.id.itemid_3:
                Toast.makeText(Profile_Bio.this,
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
        setContentView(R.layout.profile_bio);

        cancel = (Button) findViewById(R.id.cancel_action);
        save = (Button) findViewById(R.id.save_action);
        height = (TextView) findViewById(R.id.height);
        weight = (TextView) findViewById(R.id.weight);
        age = (TextView) findViewById(R.id.age);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        cancel.setOnClickListener(this);
        save.setOnClickListener(this);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        mid = getIntent().getExtras().getInt("midbio");
        DB_profile db_profile = new DB_profile(this);
        String arrData[] = db_profile.SelectData(mid);

        age.setText(arrData[9]);
        height.setText(arrData[5]);
        weight.setText(arrData[6]);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                Intent backprofile = new Intent(getApplicationContext(), Profile.class);
                backprofile.putExtra("mid", mid);
                startActivity(backprofile);
                finish();
                break;
            case R.id.save_action:
                if (male.isChecked()) {
                    bmr = 66 + (13.7 * Double.parseDouble(weight.getText().toString())) + (5
                            * Double.parseDouble(height.getText().toString())) -
                            (6.8 * Integer.parseInt(age.getText().toString()));
                    gender = "male";
                } else if (female.isChecked()) {
                    bmr = 665 + (9.6 * Double.parseDouble(weight.getText().toString())) + (1.8
                            * Double.parseDouble(height.getText().toString())) -
                            (4.7 * Integer.parseInt(age.getText().toString()));
                    gender = "female";
                }
                tdee = 1.375 * bmr;

                DB_profile db_profile = new DB_profile(this);
                db_profile.UpdateData(mid, height.getText().toString(), weight.getText().toString()
                        , age.getText().toString(), gender, bmr, tdee);


                Toast.makeText(Profile_Bio.this,
                        "Saved",
                        Toast.LENGTH_LONG).show();

                Intent reprofile = new Intent(getApplicationContext(), Profile.class);
                reprofile.putExtra("mid", mid);
                startActivity(reprofile);
                finish();
                break;
            default:
                break;

        }
    }
}
