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
import java.util.HashMap;
import java.util.Iterator;

public class AddMealClient extends AppCompatActivity {
    private DatabaseReference referenceFood;
    private DatabaseReference referenceClient;
    private String clientID;
    private FirebaseUser client;
    FoodItem current_food;

    //Category
    String[] categoryItems = {"Carbohydrates", "Proteins", "vegetables", "Fruits", "Fats"};
    AutoCompleteTextView idCategory;
    ArrayAdapter<String> adapterItemsCategory;
    String category;


    //Foods
    AutoCompleteTextView idFood;
    ArrayAdapter<String> adapterItemsFood;
    private String[] namesOfFood;
    private HashMap<String, FoodItem> foodsMap;
    String foodName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_client);

        referenceFood = FirebaseDatabase.getInstance().getReference("Foods");
        client = FirebaseAuth.getInstance().getCurrentUser();
        referenceClient = FirebaseDatabase.getInstance().getReference("Clients");
        clientID = client.getUid();

        namesOfFood = new String[10];
        foodsMap = new HashMap<>();

        //Category
        idCategory = findViewById(R.id.txtCategory);
        adapterItemsCategory = new ArrayAdapter<String>(this,R.layout.list_items, categoryItems);
        idCategory.setAdapter(adapterItemsCategory);
        idCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }
        });
//        if(category.equals("Carbohydrates")){
//            size = 13;
//        }
//        else if(category.equals("Proteins")){
//            size = 10;
//        }
//        else if(category.equals("vegetables")){
//            size = 8;
//        }
//        else if(category.equals("Fruits")){
//            size = 7;
//        }
//        else if(category.equals("Fats")){
//            size = 5;
//        }

    }


    public void OkCategory(View view){
        // get all food in this category
        referenceFood.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it = snapshot.getChildren().iterator();
                int i=0;
                while(it.hasNext()){
                    DataSnapshot food_temp = it.next();
                    String name = food_temp.child("Name").getValue().toString().trim();
                    String calories = food_temp.child("Calories").getValue().toString().trim();
                    String unit = food_temp.child("Unit").getValue().toString().trim();
                    String sodium = food_temp.child("Sodium").getValue().toString().trim();
                    String cholesterol = food_temp.child("Cholesterol").getValue().toString().trim();
                    String fats = food_temp.child("Fats").getValue().toString().trim();
                    FoodItem current_food = new FoodItem(name, calories, unit, sodium,
                            cholesterol, fats);// sugars,

                    foodsMap.put(name, current_food);

                    namesOfFood[i] = name;
                    ++i;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        Toast.makeText(getApplicationContext(), "choose an item", Toast.LENGTH_SHORT).show();

        //Foods
        idFood = findViewById(R.id.txtFood);
        adapterItemsFood = new ArrayAdapter<String>(this,R.layout.list_items, namesOfFood);
        idFood.setAdapter(adapterItemsFood);
        idFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodName = parent.getItemAtPosition(position).toString();
            }
        });

    }

    public void okFood(View view){
        current_food = foodsMap.get(foodName);
        TextView unit = (TextView)findViewById(R.id.tv_unit);
        unit.setText(current_food.Unit);
    }

    public void submitClick(View view) {
        EditText ed_amount = (EditText) findViewById(R.id.ed_amount);
        int amount = Integer.parseInt(ed_amount.getText().toString().trim());
        double totalCalories = amount*Double.parseDouble(current_food.Calories);

        TextView addMsg = (TextView)findViewById(R.id.calories);
        if (amount!=1){
            addMsg.setText(""+ amount + " " + current_food.Name+ "  were added");
        }else{
            addMsg.setText(""+ amount + " " + current_food.Name+ "  was added");
        }

        referenceClient.child(clientID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double leftCalories = Double.parseDouble(
                        snapshot.child("leftCaloriesPerDay").getValue().toString());
                double leftCalories_update = leftCalories - amount*Double.parseDouble(current_food.Calories);
                TextView tv_leftCalories_update = (TextView)findViewById(R.id.leftCalories);
                tv_leftCalories_update.setText(""+totalCalories+ " calories in " + amount + " " +
                        current_food.Name+"\n"+leftCalories_update+ " calories left fot today.");

                referenceClient.child(clientID).child("leftCaloriesPerDay").setValue(leftCalories_update)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(AddMealClient.this,
                                            "feild update leftCaloriesPerDay", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    public void addOneMore(View view) {
        Intent intent = new Intent(AddMealClient.this, AddMealClient.class);
        startActivity(intent);
        finish();
    }



    public void returnClick(View view) {
        Intent intent = new Intent(AddMealClient.this, ClientHomePage.class);
        startActivity(intent);
        finish();
    }



    public boolean notEmpty(String str){
        if(str == null){
            Toast.makeText(getApplicationContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}