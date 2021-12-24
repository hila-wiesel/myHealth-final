package com.example.myhealthnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        Toast.makeText(AdminHomePage.this,"AdminHomePage",Toast.LENGTH_LONG).show();
    }


    public void addFoodItem(View view) {
        Intent intent = new Intent(AdminHomePage.this, AddFoodAdmin.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }

    public void addEvent(View view){
        Intent intent = new Intent(AdminHomePage.this, AddEventAdmin.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }
}