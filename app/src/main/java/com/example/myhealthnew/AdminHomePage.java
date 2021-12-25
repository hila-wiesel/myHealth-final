package com.example.myhealthnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHomePage extends AppCompatActivity {

    private DatabaseReference reference;
    private String adminID;
    private FirebaseUser admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        admin = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Admins");
        adminID = admin.getUid();
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

    public void updateFoodItem(View view) {
        Intent intent = new Intent(AdminHomePage.this, AddFoodAdmin.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }

    public void logout(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(AdminHomePage.this,MainActivity.class));
        finish();
    }
}