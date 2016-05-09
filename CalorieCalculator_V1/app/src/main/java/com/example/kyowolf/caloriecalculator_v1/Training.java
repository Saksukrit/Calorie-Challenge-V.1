package com.example.kyowolf.caloriecalculator_v1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Krit on 5/10/2016.
 */
public class Training extends AppCompatActivity {
    Button btn;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

    }

    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.training);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == DILOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(Training.this, year_x + "/" + month_x + " / " + day_x, Toast.LENGTH_LONG).show();
        }
    };
}
