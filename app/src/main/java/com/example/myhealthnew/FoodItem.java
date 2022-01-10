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

    public String Name;
    public String Calories;
    public String Unit;
    public String Sodium;
    public String Cholesterol;
    public String Sugars;
    public String Fats;

    public FoodItem(String name, String calories, String unit, String sodium, String  cholesterol,
                    String sugars, String fats){
        this.Name = name;
        this.Calories = calories;
        this.Unit = unit;
        this.Sodium = sodium;
        this.Cholesterol = cholesterol;
        this.Sugars = sugars;
        this.Fats = fats;
    }

    public FoodItem(String name, String calories, String unit, String sodium, String  cholesterol,
                     String fats){
        this.Name = name;
        this.Calories = calories;
        this.Unit = unit;
        this.Sodium = sodium;
        this.Cholesterol = cholesterol;
//        this. = sugars;
        this.Fats = fats;
    }
}


