package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ClientRegiter1 extends AppCompatActivity {

    private DatabaseReference reference;
    private String UserID;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_regiter1);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Clients");
        UserID = user.getUid();
    }


    public void onClickNext(View view) {
        EditText et_Name = (EditText) findViewById(R.id.et_Name);
        EditText et_Gender = (EditText) findViewById(R.id.et_Gender);
        EditText et_City = (EditText) findViewById(R.id.et_City);
        EditText et_Country = (EditText) findViewById(R.id.et_Country);
        EditText et_Height = (EditText) findViewById(R.id.et_Height);
        EditText et_Weight = (EditText) findViewById(R.id.et_Weight);
        EditText et_PhoneNumber = (EditText) findViewById(R.id.et_PhoneNumber);
        EditText et_BirthYear = (EditText) findViewById(R.id.et_BirthYear);

        String name = et_Name.getText().toString().trim();
        String gender = et_Gender.getText().toString().trim();
        String city = et_City.getText().toString().trim();
        String country = et_Country.getText().toString().trim();
        int height = Integer.parseInt(et_Height.getText().toString().trim());
        int weight = Integer.parseInt(et_Weight.getText().toString().trim());
        int phoneNumber = Integer.parseInt(et_PhoneNumber.getText().toString().trim());
        int birthYear = Integer.parseInt(et_BirthYear.getText().toString().trim());

        //check the inputs:
        if(name.isEmpty()){
            et_Name.setError("full name is required!");
            et_Name.requestFocus();
            return;
        }
        if(city.isEmpty()){
            et_City.setError("invalid city");
            et_City.requestFocus();
            return;
        }
        if(country.isEmpty()){
            et_Country.setError("invalid country");
            et_Country.requestFocus();
            return;
        }
        if(!gender.equals("female") && !gender.equals("male")){
            et_Gender.setError("invalid. gender= male/female");
            et_Gender.requestFocus();
            return;
        }
        int age = Calendar.getInstance().get(Calendar.YEAR) - birthYear;
        double recommendedCaloriesPerDay;
        if (gender.equals("female")){
            recommendedCaloriesPerDay = getBMR_female(age, height, weight);
        }
        else{
            recommendedCaloriesPerDay = getBMR_male(age, height, weight);
        }

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = snapshot.child("email").getValue().toString();
                Toast.makeText(ClientRegiter1.this, "email: "+email, Toast.LENGTH_LONG).show();

                String password = snapshot.child("password").getValue().toString();
                Client client = new Client(email, password, name, height, weight, city,
                        country, birthYear, gender, phoneNumber, recommendedCaloriesPerDay);

                FirebaseDatabase.getInstance().getReference("Clients")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(client);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        startActivity (new Intent(ClientRegiter1.this, ClientRegister2.class));
    }

    double getBMR_female(int age, int height, int weight){
        return 4.7*age - 1.8*height +9.6*weight +655;
    }

    double getBMR_male(int age, int height, int weight){
        return 6.8*age - 5*height +13.8*weight +66;
    }
}