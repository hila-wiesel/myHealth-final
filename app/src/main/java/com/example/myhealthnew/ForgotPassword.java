package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        auth = FirebaseAuth.getInstance();
    }

    public void onClickNext(View view) {
        EditText etEmail = (EditText) findViewById(R.id.txtEmail);
        String email = etEmail.getText().toString().trim();

        //valid email check
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("invalid email address");
            etEmail.requestFocus();
            return;
        }
        //empty email check
        if (email.isEmpty()) {
            etEmail.setError("email is required!");
            etEmail.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                }
                else{
                    Toast.makeText(ForgotPassword.this,"Failed reset your password. Please try again",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onClickBack(View view) {
        startActivity(new Intent(ForgotPassword.this, MainActivity.class));
    }

}