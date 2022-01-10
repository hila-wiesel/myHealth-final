package com.example.myhealthnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddEventAdmin extends AppCompatActivity {

    //Category
    String[] categoryItems = {"Sport", "Nutrition"};
    AutoCompleteTextView idCategory;
    ArrayAdapter<String> adapterItemsCategory;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_admin);

        //Category
        idCategory = findViewById(R.id.et_category);
        adapterItemsCategory = new ArrayAdapter<String>(this,R.layout.list_items, categoryItems);
        idCategory.setAdapter(adapterItemsCategory);
        idCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "category "+category, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClickReturn(View view) {
        Intent intent = new Intent(AddEventAdmin.this, AdminHomePage.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }

    public void onClickSubmit(View view) {
        EditText et_name = (EditText) findViewById(R.id.et_nameEvent);
        EditText et_category = (EditText) findViewById(R.id.et_category);
        EditText et_date = (EditText) findViewById(R.id.et_date);
        EditText et_startTime = (EditText) findViewById(R.id.et_startTime);
        EditText et_endTime = (EditText) findViewById(R.id.et_endTime);
        EditText et_city = (EditText) findViewById(R.id.et_city);
        EditText et_street = (EditText) findViewById(R.id.et_street);
        EditText et_houseNum = (EditText) findViewById(R.id.et_houseNum);
        EditText et_registerLink = (EditText) findViewById(R.id.et_registerLink);
        EditText et_description = (EditText) findViewById(R.id.et_description);

        String name = et_name.getText().toString().trim();
        String category = et_category.getText().toString().trim();
        String date = et_date.getText().toString().trim();
        String startTime = et_startTime.getText().toString().trim();
        String endTime = et_endTime.getText().toString();
        String city = et_city.getText().toString();
        String street = et_street.getText().toString();
        String houseNum = et_houseNum.getText().toString();
        String registerLink = et_registerLink.getText().toString();
        String description = et_description.getText().toString();

        //check the inputs:
        if(name.isEmpty()){
            et_name.setError("full name is required!");
            et_name.requestFocus();
            return;
        }
        if(category.isEmpty()){
            et_category.setError("category is required!");
            et_category.requestFocus();
            return;
        }
        if(date.isEmpty()){
            et_date.setError("date is required!");
            et_date.requestFocus();
            return;
        }
        if(startTime.isEmpty()){
            et_startTime.setError("start time is required!");
            et_startTime.requestFocus();
            return;
        }
        if(endTime.isEmpty()){
            et_endTime.setError("end time is required!");
            et_endTime.requestFocus();
            return;
        }
        if(city.isEmpty()){
            et_city.setError("city is required!");
            et_city.requestFocus();
            return;
        }
        if(street.isEmpty()){
            et_street.setError("street is required!");
            et_street.requestFocus();
            return;
        }
        if(houseNum.isEmpty()){
            et_houseNum.setError("house number is required!");
            et_houseNum.requestFocus();
            return;
        }
        if(registerLink.isEmpty()){
            et_registerLink.setError("full name is required!");
            et_registerLink.requestFocus();
            return;
        }

        Event newEvent = new Event(name, category, registerLink, city, street, houseNum, date,
                startTime, endTime, description);
        FirebaseDatabase.getInstance().getReference("Events").child(city)
                .child(category).child(name).setValue(newEvent);

        Toast.makeText(AddEventAdmin.this, "event added successfully", Toast.LENGTH_SHORT).show();
        // notification...
        Intent intent = new Intent(AddEventAdmin.this, AdminHomePage.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }
}