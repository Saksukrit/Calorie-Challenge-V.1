package com.example.kyowolf.caloriecalculator_v1.Activity_Walking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Krit on 4/20/2016.
 */
public class DB_Activity extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ActivityHistory";
    private static final String TABLE_MEMBER = "Activity";

    public DB_Activity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_MEMBER +
                "(Aid INTEGER PRIMARY KEY AUTOINCREMENT," +
                " username TEXT(30) NOT NULL," +
                " duration TEXT(10) NOT NULL," +
                " distance TEXT(10) NOT NULL," +
                " speed TEXT(16) NOT NULL," +
                " calorie TEXT (10) NOT NULL," +
                " step INTEGER(10) ," +
                " date TEXT(30) NOT NULL," +
                " type TEXT(10) NOT NULL );");

        Log.d("CREATE TABLE", "Create Table Successfully.");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long InsertData(String username, String duration, String distance
            , String speed, Double calorie, int step, String date, String type) {

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            ContentValues Val = new ContentValues();
            Val.put("username", username);
            Val.put("duration", duration);
            Val.put("distance", distance);
            Val.put("speed", speed);
            Val.put("calorie", calorie);
            Val.put("step", step);
            Val.put("date", date);
            Val.put("type", type);


            long rows = db.insert(TABLE_MEMBER, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }
    }

    public String[] SelectAllData() {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_MEMBER;
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[1] = cursor.getString(1); //username
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                    arrData[4] = cursor.getString(4);
                    arrData[5] = cursor.getString(5);
                    arrData[6] = cursor.getString(6);
                    arrData[7] = cursor.getString(7);
                    arrData[8] = cursor.getString(8);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    public String[] SelectDataByDate(String date) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MEMBER, new String[]{"*"},
                    "step=?",
                    new String[]{String.valueOf("40")}, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[1] = cursor.getString(1);//username
                    arrData[2] = cursor.getString(2);
                    arrData[3] = cursor.getString(3);
                    arrData[4] = cursor.getString(4);
                    arrData[5] = cursor.getString(5);
                    arrData[6] = cursor.getString(6);
                    arrData[7] = cursor.getString(7);
                    arrData[8] = cursor.getString(8);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Delete Data
    public long DeleteAllData() {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data


            long rows = db.delete(TABLE_MEMBER, null, null);

            db.close();
            return rows; // return rows delete.

        } catch (Exception e) {
            return -1;
        }

    }




    public void rmvall() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMBER, null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = " + TABLE_MEMBER);

    }
}
