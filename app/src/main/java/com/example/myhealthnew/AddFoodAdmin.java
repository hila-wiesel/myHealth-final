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
    // for unique key:
//    int counter = 1;
//    private DatabaseReference reference;
//    private String adminID;
//    private FirebaseUser admin;

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

    String admin_name,email,
            unit, calories, sugars, sodium, fats, cholesterol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_admin);

        // for unique key:
//        admin = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Admins");
//        adminID = admin.getUid();
//
//        reference.child(adminID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                email = snapshot.child("email").getValue().toString();
//                Toast.makeText(AddFoodAdmin.this, "email: "+email, Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        //Units

        idUnit = findViewById(R.id.txtUnit);
        adapterItemsUnits = new ArrayAdapter<String>(this,R.layout.list_items, unitItems);
        idUnit.setAdapter(adapterItemsUnits);
        idUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                unit = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), "unit "+unit, Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(), "Calories "+calories, Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(), "Sugars "+sugars, Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(), "sodium "+sodium, Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(), "fats "+fats, Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(), "cholesterol "+cholesterol, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void returnClick(View view) {
        Intent intent = new Intent(AddFoodAdmin.this, AdminHomePage.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
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

        // for unique key:
//        String [] email_arr = email.split("@");
//        String admin_name = email_arr[0];
//        Toast.makeText(getApplicationContext(), "admin_name "+admin_name, Toast.LENGTH_SHORT).show();

//        if (boolCalories && boolUnit && boolSodium && boolCholesterol && boolSugars && boolFats){
//            FoodItem newFood = new FoodItem(name, calories, unit, sodium,cholesterol, sugars, fats);
//            String id = counter+admin_name;
//            Toast.makeText(getApplicationContext(), "id "+id, Toast.LENGTH_SHORT).show();
//            FirebaseDatabase.getInstance().getReference("Foods")
//                    .child(id).setValue(newFood);
//            Toast.makeText(getApplicationContext(), "the food item "+name+" successfully added\n"
//                    , Toast.LENGTH_SHORT).show();
//            counter++;
//            startActivity(( new Intent(AddFoodAdmin.this, AdminHomePage.class)));
//        }

        if (boolCalories && boolUnit && boolSodium && boolCholesterol && boolSugars && boolFats){
            FoodItem newFood = new FoodItem(name, calories, unit, sodium,cholesterol, sugars, fats);
            FirebaseDatabase.getInstance().getReference("Foods")
                    .child(name).setValue(newFood);
            Toast.makeText(getApplicationContext(), "the food item "+name+" successfully update\n"
                    , Toast.LENGTH_SHORT).show();
            startActivity(( new Intent(AddFoodAdmin.this, AdminHomePage.class)));
        }

    }

    public boolean notEmpty(String str){
        if(str == null){
            Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}