package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddFoodAdmin extends AppCompatActivity {

    //Category
    String[] categoryItems = {"Carbohydrates", "Proteins", "vegetables", "Fruits",  "Other", "Fats"};
    AutoCompleteTextView idCategory;
    ArrayAdapter<String> adapterItemsCategory;

    //Units
    String[] unitItems = {"100 grams", "teaspoon", "tablespoon", "slice", "glass", "one unit"};
    AutoCompleteTextView idUnit;
    ArrayAdapter<String> adapterItemsUnits;

    //Calories
    String[]  caloriesItems = new String[10001];
    AutoCompleteTextView idCalories;
    ArrayAdapter<String> adapterItemsCalories;

    //Sugars
    String[]  sugarsItems = new String[1001];
    AutoCompleteTextView idSugars;
    ArrayAdapter<String> adapterItemsSugars;

    //Sodium
    String[]  sodiumItems = new String[501];
    AutoCompleteTextView idSodium;
    ArrayAdapter<String> adapterItemsSodium;

    //Fats
    String[]  fatsItems = new String[501];
    AutoCompleteTextView idFats;
    ArrayAdapter<String> adapterItemsFats;

    //Fats
    String[]  cholesterolItems = new String[1301];
    AutoCompleteTextView idCholesterol;
    ArrayAdapter<String> adapterItemsCholesterol;

    String unit, calories, sugars, sodium, fats, cholesterol, category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_admin);

        //Category
        idCategory = findViewById(R.id.txtCategory);
        adapterItemsCategory = new ArrayAdapter<String>(this,R.layout.list_items, categoryItems);
        idCategory.setAdapter(adapterItemsCategory);

        idCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "test(in): "+category, Toast.LENGTH_LONG).show();
            }
        });


        //Units
        idUnit = findViewById(R.id.txtUnit);
        adapterItemsUnits = new ArrayAdapter<String>(this,R.layout.list_items, unitItems);
        idUnit.setAdapter(adapterItemsUnits);
        idUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                unit = parent.getItemAtPosition(position).toString();
            }
        });

        //Calories
        for (int i = 0; i < 10001; i++) {
            caloriesItems[i] = String.valueOf(i);
        }
        idCalories = findViewById(R.id.txtCalories);
        adapterItemsCalories = new ArrayAdapter<String>(this,R.layout.list_items, caloriesItems);
        idCalories.setAdapter(adapterItemsCalories);
        idCalories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                calories = parent.getItemAtPosition(position).toString();
            }
        });

        //Sugars
        for (int i = 0; i < 1001; i++) {
            sugarsItems[i] = String.valueOf(i);
        }
        idSugars = findViewById(R.id.txtSugars);
        adapterItemsSugars = new ArrayAdapter<String>(this,R.layout.list_items, sugarsItems);
        idSugars.setAdapter(adapterItemsSugars);
        idSugars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sugars = parent.getItemAtPosition(position).toString();
            }
        });

        //Sodium
        for (int i = 0; i < 501; i++) {
            sodiumItems[i] = String.valueOf(i);
        }
        idSodium = findViewById(R.id.txtSodium);
        adapterItemsSodium = new ArrayAdapter<String>(this,R.layout.list_items, sodiumItems);
        idSodium.setAdapter(adapterItemsSodium);
        idSodium.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sodium = parent.getItemAtPosition(position).toString();
            }
        });

        //Fats
        for (int i = 0; i < 501; i++) {
            fatsItems[i] = String.valueOf(i);
        }
        idFats = findViewById(R.id.txtFats);
        adapterItemsFats = new ArrayAdapter<String>(this,R.layout.list_items, fatsItems);
        idFats.setAdapter(adapterItemsFats);
        idFats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fats = parent.getItemAtPosition(position).toString();
            }
        });

        //Cholesterol
        for (int i = 0; i < 1301; i++) {
            cholesterolItems[i] = String.valueOf(i);
        }
        idCholesterol = findViewById(R.id.txtCholesterol);
        adapterItemsCholesterol = new ArrayAdapter<String>(this,R.layout.list_items, cholesterolItems);
        idCholesterol.setAdapter(adapterItemsCholesterol);
        idCholesterol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cholesterol = parent.getItemAtPosition(position).toString();
            }
        });

    }



    public void submitClick(View view) {
        EditText et_Name = (EditText) findViewById(R.id.et_foodName);
        String name = et_Name.getText().toString().trim();
        //check the inputs:
        if(name.isEmpty()){
            et_Name.setError("full name is required!");
            et_Name.requestFocus();
            return;
        }

        boolean boolCalories = notEmpty(calories);
        boolean boolUnit = notEmpty(unit);
        boolean boolSodium = notEmpty(sodium);
        boolean boolCholesterol = notEmpty(cholesterol);
        boolean boolSugars =  notEmpty(sugars);
        boolean boolFats = notEmpty(fats);
        boolean boolCaterory = notEmpty(category);
        if (boolCalories && boolUnit && boolSodium && boolCholesterol && boolSugars && boolFats &&
                boolCaterory){
            FoodItem newFood = new FoodItem(name, calories, unit, sodium,cholesterol, sugars, fats);
            FirebaseDatabase.getInstance().getReference("Foods").child(category)
                    .child(name).setValue(newFood);
            Toast.makeText(getApplicationContext(), "the food item "+name+" successfully update\n"
                    , Toast.LENGTH_SHORT).show();
            startActivity(( new Intent(AddFoodAdmin.this, AdminHomePage.class)));
        }
    }

    public void returnClick(View view) {
        Intent intent = new Intent(AddFoodAdmin.this, AdminHomePage.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }

    public boolean notEmpty(String str){
        if(str == null){
            Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}