package com.example.kyowolf.caloriecalculator_v1.StepCounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kyowolf.caloriecalculator_v1.Activity_Walking.DB_Activity;
import com.example.kyowolf.caloriecalculator_v1.History.History_activity;
import com.example.kyowolf.caloriecalculator_v1.Profile.DB_profile;
import com.example.kyowolf.caloriecalculator_v1.Profile.Profile_Bio;
import com.example.kyowolf.caloriecalculator_v1.R;

import java.text.DecimalFormat;
import java.util.Calendar;


@SuppressLint("HandlerLeak")
public class StepCounterActivity extends AppCompatActivity implements View.OnClickListener {

    DB_Activity db_activity = new DB_Activity(this);
    private TextView tv_show_step;
    private TextView tv_week_day;
    private TextView tv_date;

    private TextView tv_timer;

    private TextView tv_distance;
    private TextView tv_calories;
    private TextView tv_velocity;

    private Button btn_start;
    private Button btn_stop;

    private boolean isRun = false;


    private long timer = 0;
    private long startTimer = 0;

    private long tempTime = 0;

    private Double distance = 0.0;
    private Double calories = 0.0;
    private Double velocity = 0.0;

    private int step_length = 0;
    private int weight = 0;
    private int total_step = 0;

    private Thread thread;

    private TableRow hide1, hide2;
    private TextView step_counter;

    ActionBar actionBar;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            countDistance();

            if (timer != 0 && distance != 0.0) {

                calories = weight * distance * 0.001;

                velocity = distance * 1000 / timer;   //speed
            } else {
                calories = 0.0;
                velocity = 0.0;
            }

            countStep();

            tv_show_step.setText(total_step + "");

            tv_distance.setText(formatDouble(distance));
            tv_calories.setText(formatDouble(calories));
            tv_velocity.setText(formatDouble(velocity));

            tv_timer.setText(getFormatTime(timer));

            changeStep();

        }


        private void changeStep() {
            int level = StepDetector.CURRENT_SETP / 100;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.walking);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        if (SettingsActivity.sharedPreferences == null) {
            SettingsActivity.sharedPreferences = this.getSharedPreferences(
                    SettingsActivity.SETP_SHARED_PREFERENCES,
                    Context.MODE_PRIVATE);
        }

        Bundle extras = getIntent().getExtras();
        isRun = extras.getBoolean("run");

        if (thread == null) {

            thread = new Thread() {// ���߳����ڼ���ǰ����ı仯

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    super.run();
                    int temp = 0;
                    while (true) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (StepCounterService.FLAG) {
                            Message msg = new Message();
                            if (temp != StepDetector.CURRENT_SETP) {
                                temp = StepDetector.CURRENT_SETP;
                            }
                            if (startTimer != System.currentTimeMillis()) {
                                timer = tempTime + System.currentTimeMillis()
                                        - startTimer;
                            }
                            handler.sendMessage(msg);
                        }
                    }
                }
            };
            thread.start();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Log.i("APP", "on resuame.");

        addView();


        init();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }


    private void addView() {
        tv_show_step = (TextView) this.findViewById(R.id.show_step);
        tv_week_day = (TextView) this.findViewById(R.id.week_day);
        tv_date = (TextView) this.findViewById(R.id.date);

        tv_timer = (TextView) this.findViewById(R.id.timer);

        tv_distance = (TextView) this.findViewById(R.id.distance);
        tv_calories = (TextView) this.findViewById(R.id.calories);
        tv_velocity = (TextView) this.findViewById(R.id.velocity);

        btn_start = (Button) this.findViewById(R.id.start);
        btn_stop = (Button) this.findViewById(R.id.stop);


        hide1 = (TableRow) findViewById(R.id.hide1);
        hide2 = (TableRow) findViewById(R.id.hide2);
        step_counter = (TextView) findViewById(R.id.step_counter);


        if (isRun) {
            hide1.setVisibility(View.GONE);
            hide2.setVisibility(View.GONE);
            step_counter.setText("����");
        }

        Intent service = new Intent(this, StepCounterService.class);
        stopService(service);
        StepDetector.CURRENT_SETP = 0;
        tempTime = timer = 0;
        tv_timer.setText(getFormatTime(timer));
        tv_show_step.setText("0");
        tv_distance.setText(formatDouble(0.0));
        tv_calories.setText(formatDouble(0.0));
        tv_velocity.setText(formatDouble(0.0));

        handler.removeCallbacks(thread);

    }


    private void init() {

        step_length = SettingsActivity.sharedPreferences.getInt(
                SettingsActivity.STEP_LENGTH_VALUE, 70);
        weight = SettingsActivity.sharedPreferences.getInt(
                SettingsActivity.WEIGHT_VALUE, 50);

        countDistance();
        countStep();
        if ((timer += tempTime) != 0 && distance != 0.0) {

            calories = weight * distance * 0.001;

            velocity = distance * 1000 / timer;
        } else {
            calories = 0.0;
            velocity = 0.0;
        }

        tv_timer.setText(getFormatTime(timer + tempTime));

        tv_distance.setText(formatDouble(distance));
        tv_calories.setText(formatDouble(calories));
        tv_velocity.setText(formatDouble(velocity));

        tv_show_step.setText(total_step + "");

        btn_start.setEnabled(!StepCounterService.FLAG);
        btn_stop.setEnabled(StepCounterService.FLAG);

        if (StepCounterService.FLAG) {
            btn_stop.setText(getString(R.string.pause));
        } else if (StepDetector.CURRENT_SETP > 0) {
            btn_stop.setEnabled(true);
            btn_stop.setText(getString(R.string.cancel));
        }

        setDate();
    }


    int weekDay, month, day, year;

    private void setDate() {
        Calendar mCalendar = Calendar.getInstance();
        weekDay = mCalendar.get(Calendar.DAY_OF_WEEK);
        month = mCalendar.get(Calendar.MONTH) + 1;
        day = mCalendar.get(Calendar.DAY_OF_MONTH);

        year = mCalendar.get(Calendar.YEAR);

        tv_date.setText(month + getString(R.string.month) + day
                + getString(R.string.day));

        String week_day_str = new String();
        switch (weekDay) {
            case Calendar.SUNDAY:
                week_day_str = getString(R.string.sunday);
                break;

            case Calendar.MONDAY:
                week_day_str = getString(R.string.monday);
                break;

            case Calendar.TUESDAY:
                week_day_str = getString(R.string.tuesday);
                break;

            case Calendar.WEDNESDAY:
                week_day_str = getString(R.string.wednesday);
                break;

            case Calendar.THURSDAY:
                week_day_str = getString(R.string.thursday);
                break;

            case Calendar.FRIDAY:
                week_day_str = getString(R.string.friday);
                break;

            case Calendar.SATURDAY:
                week_day_str = getString(R.string.saturday);
                break;
        }
        tv_week_day.setText(week_day_str);
    }


    private String formatDouble(Double doubles) {
        DecimalFormat format = new DecimalFormat("####.##");
        String distanceStr = format.format(doubles);
        return distanceStr.equals(getString(R.string.zero)) ? getString(R.string.double_zero)
                : distanceStr;
    }

    public void onClick(View view) {
        Intent service = new Intent(this, StepCounterService.class);
        switch (view.getId()) {
            case R.id.start:

                startService(service);
                btn_start.setEnabled(false);
                btn_stop.setEnabled(true);
                btn_stop.setText(getString(R.string.pause));
                startTimer = System.currentTimeMillis();
                tempTime = timer;
                break;

            case R.id.stop:
                stopService(service);

                if (StepCounterService.FLAG && StepDetector.CURRENT_SETP > 0) {
                    btn_stop.setText(getString(R.string.cancel));
                } else {
                    StepDetector.CURRENT_SETP = 0;
                    tempTime = timer = 0;

                    btn_stop.setText(getString(R.string.pause));
                    btn_stop.setEnabled(false);

                    tv_timer.setText(getFormatTime(timer));

                    tv_show_step.setText("0");
                    tv_distance.setText(formatDouble(0.0));
                    tv_calories.setText(formatDouble(0.0));
                    tv_velocity.setText(formatDouble(0.0));

                    handler.removeCallbacks(thread);
                }
                btn_start.setEnabled(true);
                break;
            case R.id.save:
                //db_activity.DeleteAllData();
                //db_activity.rmvall();
/* test insert !!!*/

                Double calories_2 = Double.valueOf(formatDouble(calories));   // formatDouble ****
                long flg1 = db_activity.InsertData("kyo", String.valueOf(getFormatTime(timer)), formatDouble(distance).toString(), formatDouble(velocity).toString(), calories_2, total_step, day + "/" + month + "/" + year, "walking");
                if (flg1 > 0) {
                    Toast.makeText(StepCounterActivity.this, "Insert(1) Data Successfully",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(StepCounterActivity.this, "Insert(1) Data Failed.",
                            Toast.LENGTH_LONG).show();
                }


                Intent intentHistory = new Intent(getApplicationContext(), History_activity.class);
                intentHistory.putExtra("date", (day + "/" + month + "/" + year).toString());
                startActivity(intentHistory);
                finish();
                break;

        }
    }


    private String getFormatTime(long time) {
        time = time / 1000;
        long second = time % 60;
        long minute = (time % 3600) / 60;
        long hour = time / 3600;


        String strSecond = ("00" + second)
                .substring(("00" + second).length() - 2);

        String strMinute = ("00" + minute)
                .substring(("00" + minute).length() - 2);

        String strHour = ("00" + hour).substring(("00" + hour).length() - 2);

        return strHour + ":" + strMinute + ":" + strSecond;
        // + strMillisecond;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_step, menu);
        return super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_step, menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.ment_information:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void countDistance() {
        if (StepDetector.CURRENT_SETP % 2 == 0) {
            distance = (StepDetector.CURRENT_SETP / 2) * 3 * step_length * 0.01;
        } else {
            distance = ((StepDetector.CURRENT_SETP / 2) * 3 + 1) * step_length * 0.01;
        }
    }


    private void countStep() {
        if (StepDetector.CURRENT_SETP % 2 == 0) {
            total_step = StepDetector.CURRENT_SETP;
        } else {
            total_step = StepDetector.CURRENT_SETP + 1;
        }

        total_step = StepDetector.CURRENT_SETP;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }


}
