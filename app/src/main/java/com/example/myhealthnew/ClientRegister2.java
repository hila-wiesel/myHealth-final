package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ClientRegister2 extends AppCompatActivity {
    private DatabaseReference reference;
    private String UserID;
    private FirebaseUser user;

    TextView tvDay;
    boolean[] selectedDay;
    ArrayList<Integer> dayList = new ArrayList<>();
    String[] dayArray = {"Monday","Tuesday","Wednesday","Thursday",
            "Friday","Saturday","Sunday"};

    TextView tvDay2;
    boolean[] selectedhour;
    ArrayList<Integer> hourList = new ArrayList<>();
    String[] hoursArray = {"10:00","12:00","14:00","16:00","18:00","20:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register2);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Clients");
        UserID = user.getUid();

        tvDay = findViewById(R.id.tv_day);
        selectedDay = new boolean[dayArray.length];
//        tvDay2 = findViewById(R.id.tv_day2);
        selectedhour = new boolean[hoursArray.length];
        tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientRegister2.this);
                builder.setTitle("Select Hour");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(dayArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                        if (isChecked){
                            dayList.add(i);
                            Collections.sort(dayList);
                        }else{
                            dayList.remove(i);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < dayList.size(); j++){
                            stringBuilder.append(dayArray[dayList.get(j)]);
                            if (j != dayList.size() - 1){
                                stringBuilder.append(", ");
                            }
                        }
                        tvDay.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selectedDay.length; i++) {
                            selectedDay[i] = false;
                            dayList.clear();
                            tvDay.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        tvDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientRegister2.this);
                builder.setTitle("Select Day");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(hoursArray, selectedhour, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                        if (isChecked){
                            hourList.add(i);
                            Collections.sort(hourList);
                        }else{
                            hourList.remove(i);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < hourList.size(); j++){
                            stringBuilder.append(hoursArray[hourList.get(j)]);
                            if (j != hourList.size() - 1){
                                stringBuilder.append(", ");
                            }
                        }
                        tvDay2.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selectedhour.length; i++) {
                            selectedhour[i] = false;
                            hourList.clear();
                            tvDay2.setText("");
                        }
                    }
                });
                builder.show();
            }
        });
    }
    public void onClickEnd(View view) {

        FirebaseDatabase.getInstance().getReference("Clients")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("waterReminderFrequency").setValue(hourList)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (!task.isSuccessful()) {
                               Toast.makeText(ClientRegister2.this, "somthing bad happen", Toast.LENGTH_SHORT).show();

                           }
                       }
                });
        FirebaseDatabase.getInstance().getReference("Clients")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("sportReminderFrequency").setValue(dayList)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(ClientRegister2.this, "somthing bad happen", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        startActivity(new Intent(ClientRegister2.this, ClientHomePage.class));
//        startActivity(new Intent(ClientRegister2.this, homePage.class));

    }

    public void onClickBack(View view){
        startActivity(new Intent(ClientRegister2.this, ClientRegiter1.class));
        finish();

    }
}