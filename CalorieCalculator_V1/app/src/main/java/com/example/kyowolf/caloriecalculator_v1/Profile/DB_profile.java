package com.example.kyowolf.caloriecalculator_v1.Profile;

/**
 * Created by Krit on 3/28/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB_profile extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Mydatabase";
    private static final String TABLE_MEMBER = "Members";


    public DB_profile(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_MEMBER +
                "(mid INTEGER PRIMARY KEY," +
                " name TEXT(30)," +
                " username TEXT(30)," +
                " password TEXT(16)," +
                " gender TEXT(10)," +
                " height DOUBLE(3,1)," +
                " weight DOUBLE(3,1)," +
                " bmr INTEGER(10)," +
                " tdee INTEGER(10)," +
                " age INTEGER(3)," +
                " loginstatus TEXT(5));");

        Log.d("CREATE TABLE", "Create Table Successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String[] checkLoginStatus(String loginstatus) {
        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MEMBER, new String[]{"*"},
                    "loginstatus=?",
                    new String[]{String.valueOf(loginstatus)}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[0] = cursor.getString(10); //loginstatus
                    arrData[1] = cursor.getString(2);//username
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }
    }

    public void setLoginStatus(String username) {

    }


    public long InsertData(int mid, String name, String username, String password
            , String gender, float height, float weight, int bmr, int tdee, int age, String loginstatus) {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("mid", mid);
            Val.put("name", name);
            Val.put("username", username);
            Val.put("password", password);
            Val.put("gender", gender);
            Val.put("height", height);
            Val.put("weight", weight);
            Val.put("bmr", bmr);
            Val.put("tdee", tdee);
            Val.put("age", age);
            Val.put("loginstatus", loginstatus);

            long rows = db.insert(TABLE_MEMBER, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }

    }

    public String[] logincheck(String username) {
        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MEMBER, new String[]{"*"},
                    "username=?",
                    new String[]{String.valueOf(username)}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[0] = cursor.getString(2); //username
                    arrData[1] = cursor.getString(3); //password
                    arrData[2] = cursor.getString(0); //mid
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }
    }

    public String[] SelectData(int MemberID) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MEMBER, new String[]{"*"},
                    "mid=?",
                    new String[]{String.valueOf(MemberID)}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[0] = cursor.getString(0); //mid
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                    arrData[4] = cursor.getString(4);//gender
                    arrData[5] = cursor.getString(5);
                    arrData[6] = cursor.getString(6);
                    arrData[7] = cursor.getString(7);
                    arrData[8] = cursor.getString(8);
                    arrData[9] = cursor.getString(9);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }


    public long UpdateData(int mid, String height, String weight, String age, String gender, double bmr, double tdee) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data


            ContentValues Val = new ContentValues();
            Val.put("height", height);
            Val.put("weight", weight);
            Val.put("age", age);
            Val.put("gender", gender);
            Val.put("bmr", bmr);
            Val.put("tdee", tdee);

            long rows = db.update(TABLE_MEMBER, Val, " mid = ?",
                    new String[]{String.valueOf(mid)});

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }


}
