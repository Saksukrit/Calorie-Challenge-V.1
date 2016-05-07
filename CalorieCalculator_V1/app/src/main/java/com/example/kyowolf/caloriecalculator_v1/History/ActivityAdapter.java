package com.example.kyowolf.caloriecalculator_v1.History;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kyowolf.caloriecalculator_v1.R;

/**
 * Created by Krit on 5/7/2016.
 */
public class ActivityAdapter extends CursorAdapter {


    public ActivityAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.listview_row, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        /*
        ImageView imageActivity = (ImageView) view.findViewById(R.id.imageActivity);
        imageActivity.set(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        */

        TextView topic = (TextView) view.findViewById(R.id.topic);
        topic.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
    }
}
