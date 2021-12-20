package com.example.myhealthnew;

public class FoodItem {
    enum Unit {
        grams_100,
        easpoon,
        tablespoon,
        slice,
        dose,
        glass,
        oneUnit
    }

    public String name;
    public int calories;
    public Unit unit;

    public int proteins;
    public int sodium;
    public int cholesterol;
    public int water;
    public int sugars;
    public int fats;

    public FoodItem(String name, int calories, Unit unit){
        this.name = name;
        this.calories = calories;
        this.unit = unit;
    }
}


