package com.example.myhealthnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class ClientRegiter1 extends AppCompatActivity {

    private StorageReference storageRef;
    private DatabaseReference reference;
    private String UserID;
    private FirebaseUser user;
    ImageView imageView;
    Button btAddImage;
    private StorageReference mStorageRef;

    //Gender
    String[] genderItems = {"Female", "Male"};
    AutoCompleteTextView idGender;
    ArrayAdapter<String> adapterItemsGender;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_regiter1);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Clients");
        UserID = user.getUid();
        storageRef = FirebaseStorage.getInstance().getReference("Clients");


        //Gender
        idGender = findViewById(R.id.txtGender);
        adapterItemsGender = new ArrayAdapter<String>(this,R.layout.list_items, genderItems);
        idGender.setAdapter(adapterItemsGender);
        idGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }
        });

        //firebase camera
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //start camera
        //        //Assign Variable
//        imageView = findViewById(R.id.image_view);
//        btAddImage = findViewById(R.id.btn_addImage);
//        if (ContextCompat.checkSelfPermission(ClientRegiter1.this,
//                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(ClientRegiter1.this,
//                    new String[]{
//                            Manifest.permission.CAMERA
//                    },100);
//        }
//
//        btAddImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 100);
//
//            }
//        });
        //end camera
    }

// start function for camera

//    private void checkPermission(){
//        if(ContextCompat.checkSelfPermission(ClientRegiter1.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(ClientRegiter1.this,new String[]{Manifest.permission.CAMERA},101);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 102){
//            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,102);
//            }
//        }
//    }
//
//
//    public void uploadImage(View v){
//        checkPermission();
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 102);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(ClientRegiter1.this, "enter onActivityResult", Toast.LENGTH_SHORT).show();
//
//            Toast.makeText(ClientRegiter1.this, "RESULT_OK", Toast.LENGTH_SHORT).show();
//            if(requestCode == 102){
//                Toast.makeText(ClientRegiter1.this, "requestCode == 101", Toast.LENGTH_SHORT).show();
//
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
//                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),imageBitmap,"IMG_" + Calendar.getInstance().getTime(),null);
//                Uri img_uri = Uri.parse(path);
//                storageRef.child(UserID).putFile(img_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(ClientRegiter1.this, "onSuccess1", Toast.LENGTH_SHORT).show();
//
//                        storageRef.child(UserID).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                reference.child(UserID).child("imageLink").setValue(uri.toString());
//                                Toast.makeText(ClientRegiter1.this, "onSuccess2", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }
//                });
//
////                onCaptureResult(data);
//            }
//
//
//    }
//
//    private void onCaptureResult(Intent data) {
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        byte bb[] = bytes. toByteArray();
//        imageView.setImageBitmap(thumbnail);
//        uploadToFirebase(bb);
//    }
//
//    private void uploadToFirebase(byte[] bb) {
//        reference.child(UserID).child("profileImage").setValue(bb);
//        StorageReference sr = mStorageRef.child("imageView/a.jpg");
//        sr.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
//                Toast.makeText(ClientRegiter1.this, "Successfully upload", Toast.LENGTH_SHORT).show();
//            }
//
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(ClientRegiter1.this, "Fail upload", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }
// end function for camera

    public void onClickNext(View view) {


        EditText et_Name = (EditText) findViewById(R.id.et_Name);
//        EditText et_Gender = (EditText) findViewById(R.id.et_Gender);
        EditText et_City = (EditText) findViewById(R.id.et_City);
        EditText et_Country = (EditText) findViewById(R.id.et_Country);
        EditText et_Height = (EditText) findViewById(R.id.et_Height);
        EditText et_Weight = (EditText) findViewById(R.id.et_Weight);
        EditText et_PhoneNumber = (EditText) findViewById(R.id.et_PhoneNumber);
        EditText et_BirthYear = (EditText) findViewById(R.id.et_BirthYear);

        String name = et_Name.getText().toString().trim();
//        String gender = et_Gender.getText().toString().trim();
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

        if (birthYear> Calendar.getInstance().get(Calendar.YEAR) || birthYear<Calendar.getInstance().get(Calendar.YEAR)-120){
            et_BirthYear.setError("invalid birth year");
            et_BirthYear.requestFocus();
            return;
        }
        int age = Calendar.getInstance().get(Calendar.YEAR) - birthYear;
        double recommendedCaloriesPerDay;
        if (gender.equals("Female")){
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

                String password = snapshot.child("firstPassword").getValue().toString();
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