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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
    }

    public void onClickSign(View view) {
        EditText etEmail = (EditText) findViewById(R.id.et_Email_main);
        EditText etPass = (EditText) findViewById(R.id.et_Password_main);
        String email = etEmail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

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
        //valid password check
        if (pass.isEmpty() || pass.length() < 6) {
            etPass.setError("min password should be 6 characters");
            etPass.requestFocus();
            return;
        }

        //login with email and password
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //login successful
                if(task.isSuccessful()){
                    if (isAdmin(email)){
                        startActivity(new Intent(MainActivity.this,AdminHomePage.class));
                    }
                    else{
                        startActivity(new Intent(MainActivity.this,ClientHomePage.class));
                    }
                }
                //login faild
                else{
                    Toast.makeText(MainActivity.this,"Incorrect password. Please try again",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onClickCreate(View view) { //register
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        finish();///
    }


    public void onClickForgotPass(View view) {
        Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
        Bundle b = new Bundle();
        b.putInt("key", 1);
        startActivity(intent);
        finish();
    }

    boolean isAdmin(String email){
        String admin_str = "admin";
        int i;
        for (i=0; i<email.length(); ++i){
            if (email.charAt(i) == '@'){
                ++i;
                break;
            }
        }
        for (int j=0; j<admin_str.length(); ++j, ++i){
            if (admin_str.charAt(j) != email.charAt(i)){
                return false;
            }
        }
        if (email.charAt(i) != '.'){
            return false;
        }
        return true;
    }

}
