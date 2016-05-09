package com.example.kyowolf.caloriecalculator_v1.Diet2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kyowolf.caloriecalculator_v1.R;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<itemOrder> {
    FoodAdapter(Context context, ArrayList<itemOrder> ob) {
        super(context, 0, ob);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemOrder qrcodes = getItem(position);
        qrcodes.toString();

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_food_adapter, parent,false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtType);
        TextView time = (TextView) convertView.findViewById(R.id.name);

        icon.setImageResource(qrcodes.getIcon());
        txtName.setText(qrcodes.getType().toString());
        time.setText(String.valueOf(qrcodes.getName()).toString());
        return convertView;


    }



}