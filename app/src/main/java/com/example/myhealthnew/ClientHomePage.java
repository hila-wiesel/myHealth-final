package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Iterator;

public class ClientHomePage extends AppCompatActivity implements  View.OnClickListener {
    private DatabaseReference reference;
    private String UserID;
    private FirebaseUser user;
    DrawerLayout drawerLayout;
    ImageView events, meal;
    String leftCalories;
    String recommendedCalories;
    boolean newDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Clients");
        UserID = user.getUid();

        events = (ImageView) findViewById(R.id.event);
        meal = (ImageView) findViewById(R.id.meal);
        events.setOnClickListener(this);
        meal.setOnClickListener(this);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String last_restart = snapshot.child("lastRestart").getValue().toString().trim();
                if (!last_restart.equals(new Date().toString())){
                    newDay = true;
                    reference.child(UserID).child("lastRestart").setValue(new Date().toString());
                } else{
                    newDay = false;
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                leftCalories = snapshot.child("leftCaloriesPerDay").getValue().toString().trim();
                recommendedCalories =  snapshot.child("recommendedCaloriesPerDay").getValue().toString().trim();
//                picture = snapshot.child("picture").getValue().toString().trim();
//                Toast.makeText(ClientHomePage.this, "leftCalories: "+leftCalories, Toast.LENGTH_LONG).show();
                if (newDay){
                    TextView eventView = (TextView)findViewById(R.id.leftCalories);
                    eventView.setText(recommendedCalories);
                }else{
                    TextView eventView = (TextView)findViewById(R.id.leftCalories);
                    eventView.setText(leftCalories);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });





    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.event) {
            Intent seeEvents = new Intent(ClientHomePage.this, SeeEventsClient.class);
            startActivity(seeEvents);
        }
        else if(v.getId() == R.id.meal){
            Intent addMeal = new Intent(ClientHomePage.this, AddMealClient.class);
            startActivity(addMeal);
        }
    }
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuProfile){
            Intent addMeal = new Intent(ClientHomePage.this, ClientRegiter1.class);
            startActivity(addMeal);
            return true;
        }
        else if(id == R.id.menuNotifications){
            Intent addMeal = new Intent(ClientHomePage.this, ClientRegister2.class);
            startActivity(addMeal);
            return true;
        }
        else if(id == R.id.menuLogOut){
            logout(this);
        }
        return super.onOptionsItemSelected(item);
    }


}


        //before:
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_client_home_page);
//        Toast.makeText(ClientHomePage.this,"ClientHomePage",Toast.LENGTH_LONG).show();
//
//        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
//
//        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//        NavigationView navigationView = findViewById(R.id.navigationView);
//        navigationView.setItemIconTintList(null);
//
//    }
//
//    public void addMeal(View view){
//        Intent intent = new Intent(ClientHomePage.this, AddMealClient.class);
//        Bundle b = new Bundle();
//        startActivity(intent);
//        finish();
//    }
//
//    public void seeEvents(View view) {
//        Intent intent = new Intent(ClientHomePage.this, SeeEventsClient.class);
//        Bundle b = new Bundle();
//        b.putInt("key", 1);
//        startActivity(intent);
//        finish();
//    }
