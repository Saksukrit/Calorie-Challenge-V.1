package com.example.kyowolf.caloriecalculator_v1.History;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Krit on 5/7/2016.
 */
public class DB_ActivityHelper {

    private static final String TAG = DB_ActivityHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ActivityHistory2";

    private static final String TABLE_NAME = "Activity";
    private static final String TABLE_COLUMN_ID = "Aid";
    private static final String TABLE_COLUMN_USERNAME = "username";
    private static final String TABLE_COLUMN_DURATION = "duration";
    private static final String TABLE_COLUMN_DISTANCE = "distance";
    private static final String TABLE_COLUMN_SPEED = "speed";
    private static final String TABLE_COLUMN_CALORIE = "calorie";
    private static final String TABLE_COLUMN_DATE = "date";
    private static final String TABLE_COLUMN_TIME = "time";
    private static final String TABLE_COLUMN_TYPE = "type";
    private static final String TABLE_COLUMN_STEP = "step";

    private DB_Activity db_activity;
    private SQLiteDatabase database;

    public DB_ActivityHelper(Context aContext) {
        db_activity = new DB_Activity(aContext);
        database = db_activity.getWritableDatabase();
    }

    public long InsertDataWalking(String username, String duration, String distance
            , String speed, Double calorie, int step, String date, String type, String time) {

        ContentValues Val = new ContentValues();
        Val.put(TABLE_COLUMN_USERNAME, username);
        Val.put(TABLE_COLUMN_DURATION, duration);
        Val.put(TABLE_COLUMN_DISTANCE, distance);
        Val.put(TABLE_COLUMN_SPEED, speed);
        Val.put(TABLE_COLUMN_CALORIE, calorie);
        Val.put(TABLE_COLUMN_STEP, step);
        Val.put(TABLE_COLUMN_DATE, date);
        Val.put(TABLE_COLUMN_TYPE, type);
        Val.put(TABLE_COLUMN_TIME, time);

        database.insert(TABLE_NAME, null, Val);
        database.close();

        return -1;
    }

    public long InsertDataRunning(String username, String duration, String distance
            , String speed, Double calorie, String date, String type, String time) {

        ContentValues Val = new ContentValues();
        Val.put(TABLE_COLUMN_USERNAME, username);
        Val.put(TABLE_COLUMN_DURATION, duration);
        Val.put(TABLE_COLUMN_DISTANCE, distance);
        Val.put(TABLE_COLUMN_SPEED, speed);
        Val.put(TABLE_COLUMN_CALORIE, calorie);
        Val.put(TABLE_COLUMN_DATE, date);
        Val.put(TABLE_COLUMN_TYPE, type);
        Val.put(TABLE_COLUMN_TIME, time);

        database.insert(TABLE_NAME, null, Val);
        database.close();

        return -1;
    }

    public Cursor SelectAllData() {

        try {

        /*
            String arrData[] = null;

            Cursor cursor = database.query(TABLE_NAME, new String[]{TABLE_COLUMN_TYPE, TABLE_COLUMN_DATE, TABLE_COLUMN_TIME}, null, null, null, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    arrData[1] = cursor.getString(1);//type
                    arrData[2] = cursor.getString(2);//date
                    arrData[3] = cursor.getString(3);//time
                }
            }
            cursor.close();
            database.close();
            */
            String buildSQL = "SELECT " + TABLE_COLUMN_TYPE + "," + TABLE_COLUMN_DATE + "," + TABLE_COLUMN_TIME
                    + " FROM " + TABLE_NAME;

            Log.d(TAG, "getAllData SQL: " + buildSQL);
            return database.rawQuery(buildSQL, null);
        } catch (Exception e) {
            return null;
        }

    }

    public String[] SelectDataByDate(String date) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            Cursor cursor = database.query(TABLE_NAME, new String[]{"*"},
                    "step=?",
                    new String[]{String.valueOf("13")}, null, null, null, null);

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
                    arrData[9] = cursor.getString(9);
                }
            }
            cursor.close();
            database.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }

    // Delete Data
    public void DeleteAllData() {
        Log.d(TAG, "DeleteAllData SQL:" + TABLE_NAME);

        database.delete(TABLE_NAME, null, null);
        database.close();
    }

    // Delete by ID
    public void DeleteByID(String id) {
        Log.d(TAG, "DeleteAllData SQL:" + TABLE_NAME);

        String[] args = {id};
        database.delete(TABLE_NAME, TABLE_COLUMN_ID, args);
        database.close();
    }


    public static class DB_Activity extends SQLiteOpenHelper {

        public DB_Activity(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String buildSQL = "CREATE TABLE " + TABLE_NAME +
                    "(" + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TABLE_COLUMN_USERNAME + " TEXT(30) NOT NULL," +
                    TABLE_COLUMN_DURATION + "  TEXT(10) NOT NULL," +
                    TABLE_COLUMN_DISTANCE + "  TEXT(10) NOT NULL," +
                    TABLE_COLUMN_SPEED + "  TEXT(16) NOT NULL," +
                    TABLE_COLUMN_CALORIE + "  TEXT (10) NOT NULL," +
                    TABLE_COLUMN_STEP + "  INTEGER(10) ," +
                    TABLE_COLUMN_DATE + "  TEXT(30) NOT NULL," +
                    TABLE_COLUMN_TIME + "  TEXT(30) NOT NULL," +
                    TABLE_COLUMN_TYPE + "  TEXT(10) NOT NULL );";

            sqLiteDatabase.execSQL(buildSQL);

            Log.d(TAG, "Create Table Successfully.");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

            String buildSQL = "DROP TABLE IF EXISTS" + TABLE_NAME;

            Log.d(TAG, "Upgrade TABLE : " + buildSQL);

            sqLiteDatabase.execSQL(buildSQL);

            onCreate(sqLiteDatabase);
        }


    }
}
