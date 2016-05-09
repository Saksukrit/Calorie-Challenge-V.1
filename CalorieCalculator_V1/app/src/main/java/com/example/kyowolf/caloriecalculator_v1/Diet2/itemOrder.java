package com.example.kyowolf.caloriecalculator_v1.Diet2;


public class itemOrder {
    int icon;
    String type;
    String name;

    public itemOrder(int icon, String type, String name) {
        this.icon = icon;
        this.type = type;
        this.name = name;
    }


    public int getIcon() {return icon;}
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }




}
