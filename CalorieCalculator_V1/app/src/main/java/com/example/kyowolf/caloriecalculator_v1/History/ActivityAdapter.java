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
    String[] strName;
    int[] resId;

    public ActivityAdapter(Context context, String[] strName, int[] resId) {
        this.mContext = context;
        this.strName = strName;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return strName.length;
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
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.detail);
        textView.setText(strName[position]);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(resId[position]);

        return view;
    }
}
