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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
//    private DatabaseReference reference;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        //UserID = mAuth.getCurrentUser().getUid();
//        UserID = mAuth.getUid();
    }

    public void onClickNext(View view) {
        EditText etEmail = (EditText) findViewById(R.id.et_Email_CreateClient);
        EditText etPass1 = (EditText) findViewById(R.id.et_Pass_CreateClient);
        EditText etPass2 = (EditText) findViewById(R.id.et_Pass2_CreateClient);
        String email = etEmail.getText().toString().trim();
        String pass1 = etPass1.getText().toString().trim();
        String pass2 = etPass2.getText().toString().trim();


        if (!pass1.equals(pass2)) {
            Toast.makeText(Register.this, "the password doesn't match", Toast.LENGTH_SHORT).show();
        }
        else{   // password  match

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
        if (pass1.isEmpty() || pass1.length() < 6) {
            etPass1.setError("min password should be 6 characters");
            etPass1.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(Register.this, "email: " + email+ " pass: " + pass1 ,Toast.LENGTH_LONG).show();

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "register success", Toast.LENGTH_SHORT).show();

                            // Administrator or Client:
                            if (isAdmin(email)) {
                                User user = new Administrator(email, pass1);
                                FirebaseDatabase.getInstance().getReference("Admins")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    mAuth.signInWithEmailAndPassword(email, pass1)
                                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                    Intent intent = new Intent(Register.this, AdminHomePage.class);
                                                                    startActivity(intent);
                                                                }
                                                            });
                                                }
                                            }
                                        });

                            }
                            else {  //Client
                                User user = new User(email, pass1);
                                FirebaseDatabase.getInstance().getReference("Clients")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    mAuth.signInWithEmailAndPassword(email, pass1)
                                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                    Intent intent = new Intent(Register.this, ClientRegiter1.class);
                                                                    startActivity(intent);
                                                                }
                                                            });
                                                }
                                            }
                                        });
                            }
                        }
                        else {
                            Toast.makeText(Register.this, "register failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        }
    }

    public void onClickBack(View view) {
        startActivity(new Intent(Register.this, MainActivity.class));
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