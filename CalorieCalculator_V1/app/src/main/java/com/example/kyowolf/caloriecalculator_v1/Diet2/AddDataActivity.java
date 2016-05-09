package com.example.kyowolf.caloriecalculator_v1.Diet2;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.kyowolf.caloriecalculator_v1.R;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
    }
    public void ClickItem(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.type1:
                if (checked)

                    break;
            case R.id.type2:
                if (checked)

                    break;
            case R.id.type3:
                if (checked)

                    break;
            case R.id.type4:
                if (checked)

                    break;
        }
    }

    public void btnClicks(View view)  {
        EditText name = (EditText) findViewById(R.id.name);
        EditText num = (EditText) findViewById(R.id.howmuch);
        String txt = name.getText().toString();


        RadioButton r1 = (RadioButton) findViewById(R.id.type1);
        RadioButton r2 = (RadioButton) findViewById(R.id.type2);
        RadioButton r3 = (RadioButton) findViewById(R.id.type3);
        RadioButton r4 = (RadioButton) findViewById(R.id.type4);
        if(r1.isChecked()) {
            String a= "FOOD :" +"  "+txt;
            String b = num.getText().toString() + "  แคลอรี่";
            int icon = R.drawable.ff;

            Uri u =Uri.parse("content://food");
            ContentValues cv = new ContentValues();
            cv.put("icon",icon);
            cv.put("productname", a);
            cv.put("timeDate", b);
            Uri nari = getContentResolver().insert(u, cv);
            Intent intent = new Intent(getApplicationContext(), FoodListActivity.class);
            startActivity(intent);
            finish();
        }
        else if(r2.isChecked()) {
            String a= "Dessert :" +"  "+txt;
            String b = num.getText().toString() + "  แคลอรี่";
            int icon = R.drawable.ss;

            Uri u =Uri.parse("content://Dessert");
            ContentValues cv = new ContentValues();
            cv.put("icon",icon);
            cv.put("productname", a);
            cv.put("timeDate", b);
            Uri nari = getContentResolver().insert(u, cv);
            Intent intent = new Intent(getApplicationContext(), DessertListActivity.class);
            startActivity(intent);
            finish();
        }
        else if(r3.isChecked()) {
            String a= "Fruit :" +"  "+txt;
            String b = num.getText().toString() + "  แคลอรี่";
            int icon = R.drawable.bb;

            Uri u =Uri.parse("content://Fruit");
            ContentValues cv = new ContentValues();
            cv.put("icon",icon);
            cv.put("productname", a);
            cv.put("timeDate", b);
            Uri nari = getContentResolver().insert(u, cv);
            Intent intent = new Intent(getApplicationContext(), FruitListActivity.class);
            startActivity(intent);
            finish();
        }
        else if(r4.isChecked()) {
            String a= "Drink :" +"  "+txt;
            String b = num.getText().toString() + "  แคลอรี่";
            int icon = R.drawable.ccc;

            Uri u =Uri.parse("content://drink");
            ContentValues cv = new ContentValues();
            cv.put("icon",icon);
            cv.put("productname", a);
            cv.put("timeDate", b);
            Uri nari = getContentResolver().insert(u, cv);
            Intent intent1 = new Intent(getApplicationContext(), DrinkListActivity.class);
            startActivity(intent1);
            finish();
        }
    }



}
