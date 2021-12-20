package com.example.myhealthnew;

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
import android.widget.Toast;

import java.util.ArrayList;

public class AddFoodAdmin extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_admin);

        //Units

        idUnit = findViewById(R.id.txtUnit);
        adapterItemsUnits = new ArrayAdapter<String>(this,R.layout.list_items, unitItems);
        idUnit.setAdapter(adapterItemsUnits);
        idUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+item, Toast.LENGTH_SHORT).show();
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
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+item, Toast.LENGTH_SHORT).show();
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
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+item, Toast.LENGTH_SHORT).show();
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
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+item, Toast.LENGTH_SHORT).show();
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
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+item, Toast.LENGTH_SHORT).show();
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
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+item, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void returnClick(View view) {
        Intent intent = new Intent(AddFoodAdmin.this, AdminHomePage.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();///
    }

    public void submitClick(View view) {
        startActivity(( new Intent(AddFoodAdmin.this, AdminHomePage.class)));
    }
}