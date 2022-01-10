package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class SeeEventsAdmin extends AppCompatActivity {

    private DatabaseReference reference;
    private Event[] events;
    private int index=0;
    String category, city;
    boolean hasEvents = false;

    //Category
    String[] categoryItems = {"Sport", "Nutrition"};
    AutoCompleteTextView idCategory;
    ArrayAdapter<String> adapterItemsCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_events_admin);

        //Category
        idCategory = findViewById(R.id.et_category);
        adapterItemsCategory = new ArrayAdapter<String>(this,R.layout.list_items, categoryItems);
        idCategory.setAdapter(adapterItemsCategory);
        idCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), "category: "+category, Toast.LENGTH_SHORT).show();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Events");
        events = new Event[100];
    }


    public void seeEvents(View view){
        EditText et_city = (EditText) findViewById(R.id.et_city_seeEvents);
        city = et_city.getText().toString().trim();

        if (city.isEmpty()){
            et_city.setError("city is required!");
            et_city.requestFocus();
            return;
        }

        reference.child(city).child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                int i=0;
                while(it.hasNext()){
                    hasEvents = true;
                    DataSnapshot event = it.next();
                    String name = event.child("name").getValue().toString().trim();
                    String category = event.child("category").getValue().toString().trim();
                    String registerLink = event.child("registerLink").getValue().toString().trim();
                    String city = event.child("city").getValue().toString().trim();
                    String street = event.child("street").getValue().toString().trim();
                    String houseNum = event.child("houseNum").getValue().toString().trim();
                    String date = event.child("date").getValue().toString().trim();
                    String startTime = event.child("startTime").getValue().toString().trim();
                    String endTime = event.child("endTime").getValue().toString().trim();
                    String description = event.child("description").getValue().toString().trim();
                    Event current_event = new Event(name, category, registerLink, city, street,
                            houseNum, date, startTime, endTime,description);
                    events[i] = current_event;
                    ++i;
                }
                if (!hasEvents){
                    TextView eventView = (TextView)findViewById(R.id.eventView);
                    eventView.setText("There is no " + category + " events in " + city + ".");
                }else{
                    TextView eventName = (TextView)findViewById(R.id.eventName);
                    eventName.setText(events[index].name);
                    TextView eventView = (TextView)findViewById(R.id.eventView);
                    eventView.setText(events[index].toString());
                    ++index;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void nextEvent(View view){
        EditText et_city = (EditText) findViewById(R.id.et_city_seeEvents);
        city = et_city.getText().toString().trim();

        if (city.isEmpty()){
            et_city.setError("city is required!");
            et_city.requestFocus();
            return;
        }
        if (category.isEmpty()){
            EditText et_category = (EditText) findViewById(R.id.et_category);
            et_category.setError("category is required!");
            et_category.requestFocus();
            return;
        }


        reference.child(city).child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it2 = snapshot.getChildren().iterator();
                int i=0;
                while(it2.hasNext()){
                    hasEvents = true;
                    DataSnapshot event = it2.next();
                    String name = event.child("name").getValue().toString().trim();
                    String category = event.child("category").getValue().toString().trim();
                    String registerLink = event.child("registerLink").getValue().toString().trim();
                    String city = event.child("city").getValue().toString().trim();
                    String street = event.child("street").getValue().toString().trim();
                    String houseNum = event.child("houseNum").getValue().toString().trim();
                    String date = event.child("date").getValue().toString().trim();
                    String startTime = event.child("startTime").getValue().toString().trim();
                    String endTime = event.child("endTime").getValue().toString().trim();
                    String description = event.child("description").getValue().toString().trim();
                    Event current_event = new Event(name, category, registerLink, city, street,
                            houseNum, date, startTime, endTime,description);
                    events[i] = current_event;
                    ++i;
                }
                if (hasEvents == false){
                    TextView eventView = (TextView)findViewById(R.id.eventView);
                    eventView.setText("There is no " + category + " events in " + city + ".");
                }else if (events[index]==null){
                    TextView eventName = (TextView)findViewById(R.id.eventName);
                    eventName.setText("");
                    TextView eventView = (TextView)findViewById(R.id.eventView);
                    eventView.setText("No more " + category + " events in " + city + ".");
                } else{
                    TextView eventName = (TextView)findViewById(R.id.eventName);
                    eventName.setText(events[index].name);
                    TextView eventView = (TextView)findViewById(R.id.eventView);
                    eventView.setText(events[index].toString());
                    ++index;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

//        if (events[index]==null){
//            TextView eventName = (TextView)findViewById(R.id.eventName);
//            eventName.setText("");
//            TextView eventView = (TextView)findViewById(R.id.eventView);
//            eventView.setText("No more " + category + " events in " + city + ".");
//        } else{
//            TextView eventName = (TextView)findViewById(R.id.eventName);
//            eventName.setText(events[index].name);
//            TextView eventView = (TextView)findViewById(R.id.eventView);
//            eventView.setText(events[index].toString());
//            ++index;
//        }
    }

    public void returnClick(View view) {
        startActivity(new Intent(SeeEventsAdmin.this, AdminHomePage.class));
        finish();
    }


//    private boolean isAdmin(){
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        DatabaseReference referenceClient = FirebaseDatabase.getInstance().getReference("Clients");
//        DatabaseReference referenceAdmins = FirebaseDatabase.getInstance().getReference("Admins");
//        String userId = user.getUid();
//
//        if (referenceClient.child(userId).get() == null){
//            return true;
//        }
//        return false;
//    }
}