package com.example.kyowolf.caloriecalculator_v1.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kyowolf.caloriecalculator_v1.R;

/**
 * Created by Krit on 5/7/2016.
 */
public class ActivityAdapter extends BaseAdapter {
    Context mContext;
    String[] topic;
    int[] id;


    public ActivityAdapter(Context context, String[] topic, int[] id) {
        this.mContext = context;
        this.topic = topic;
        this.id = id;
    }

    @Override
    public int getCount() {
        return topic.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        View row = mInflater.inflate(R.layout.listview_row, parent, false);


        TextView textView = (TextView) row.findViewById(R.id.topic);
        textView.setText(topic[position]);

        ImageView imageView = (ImageView) row.findViewById(R.id.imageActivity);
        imageView.setBackgroundResource(id[position]);


        return row;
    }
}
