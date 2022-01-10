package com.example.myhealthnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHomePage extends AppCompatActivity {

    private DatabaseReference reference;
    private String adminID;
    private FirebaseUser admin;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        admin = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Admins");
        adminID = admin.getUid();
        dialog = new Dialog(this);
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

    public void seeEvents(View view) {
        Intent intent = new Intent(AdminHomePage.this, SeeEventsAdmin.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }

    public void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminHomePage.this,MainActivity.class));
                finish();
                dialog.dismiss();
//                activity.finishAffinity();
//                System.exit(0);
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
//
//    public void logout(View v) {
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT ;
//        int width = (int)(getResources().getDisplayMetrics().widthPixels*1);
//        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.40);
//        dialog.show();
//        dialog.getWindow().setLayout(width,height);
//        dialog.setContentView(R.layout.logout_dialog);
//        Button ok = dialog.findViewById(R.id.btn_YES);
//        Button cancel = dialog.findViewById(R.id.btn_NO);
//        TextView textView = dialog.findViewById(R.id.msg_box);
//        textView.setText("Are you sure you want to Log out?");
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(AdminHomePage.this,MainActivity.class));
//                finish();
//                dialog.dismiss();
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//    }

}