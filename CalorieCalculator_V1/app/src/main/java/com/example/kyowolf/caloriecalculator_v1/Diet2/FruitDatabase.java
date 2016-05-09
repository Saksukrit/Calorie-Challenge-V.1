package com.example.kyowolf.caloriecalculator_v1.Diet2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class FruitDatabase extends ContentProvider {

    public static final String COL_ID = "_id";


    public class MyDatabase extends SQLiteOpenHelper {

        public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE Fruit ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,icon INTEGER, productname TEXT,  timeDate TEXT)";
            db.execSQL(sql);
            Log.d("products", "table created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXITS products");
            onCreate(db);
            Log.d("products", "table upgraded");
        }
    }




    public FruitDatabase() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mydb.getWritableDatabase();
        int row = db.delete("Fruit", selection, selectionArgs);
        Log.d("producted", "delete completed");
        return  row;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = mydb.getWritableDatabase();
        long rowid = db.insert("Fruit", null, values);
        Uri nuri = ContentUris.withAppendedId(uri, rowid);

        Log.d("producted", "insert completed");
        return nuri;
    }

    MyDatabase mydb;
    @Override
    public boolean onCreate() {
        mydb = new MyDatabase(getContext(), "Fruit", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor c = db.query("Fruit", projection, selection, selectionArgs, null, null, sortOrder);
        Log.d("productdb", "query completed");
        return c;

    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mydb.getWritableDatabase();
        int row = db.update("Fruit", values, selection, selectionArgs);
        Log.d("producted", "update completed");
        return row;

    }
}
