package com.example.kyowolf.caloriecalculator_v1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by KyoWolf on 08-Mar-16.
 */
public class LoginMainActivity extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    private static Button login_btn;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        LoginButton();


    }

    public void LoginButton() {

        username = (EditText) findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText_password);
        login_btn = (Button) findViewById(R.id.button_login);

        DB_profile db_profile = new DB_profile(this);
        final String arrData[] = db_profile.logincheck(username.getText().toString());


        login_btn.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().equals(arrData[0]) &&
                                password.getText().toString().equals(arrData[1])) {
                            Toast.makeText(LoginMainActivity.this, "usre and password is correct",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Profile.class);
                            int id = Integer.parseInt(arrData[2]);
                            intent.putExtra("mid", id);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginMainActivity.this, "usre and password is incorrect",
                                    Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                        }

                    }
                }
        );

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
