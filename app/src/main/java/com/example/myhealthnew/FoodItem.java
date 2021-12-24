package com.example.myhealthnew;

public class FoodItem {
//    enum Unit {
//        grams_100,
//        easpoon,
//        tablespoon,
//        slice,
//        dose,
//        glass,
//        oneUnit
//    }

    public String name;
    public String calories;
    public String unit;
    public String sodium;
    public String cholesterol;
    public String sugars;
    public String fats;

    public FoodItem(String name, String calories, String unit, String sodium, String  cholesterol,
                    String sugars, String fats){
        this.name = name;
        this.calories = calories;
        this.unit = unit;
        this.sodium = sodium;
        this.cholesterol = cholesterol;
        this.sugars = sugars;
        this.fats = fats;
    }
}


