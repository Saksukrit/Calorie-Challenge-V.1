package com.example.kyowolf.caloriecalculator_v1;

/**
 * Created by KyoWolf on 2/24/2016.
 */
public class Order {
    public int id = 0;
    public String name = "";
    public String type = "";
    public String calorie = "";


    @Override
    public String toString() {
        return name+"  ("+calorie+")";
    }
}
