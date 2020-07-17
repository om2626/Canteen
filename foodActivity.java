package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class foodActivity extends AppCompatActivity {

    private Button Upload;
    private EditText etFname, etCalorie, etPrice;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Upload = findViewById(R.id.uploadBtn);
        etFname = findViewById(R.id.etFname);
        etCalorie = findViewById(R.id.etCalorie);
        etPrice = findViewById(R.id.etPrice);
        db = FirebaseFirestore.getInstance();

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fname = etFname.getText().toString();
                String Calorie = etCalorie.getText().toString();
                String Price = etPrice.getText().toString();
                Map<String, String> foodMap = new HashMap<>();

                foodMap.put("Food Name",Fname);
                foodMap.put("Calorie",Calorie);
                foodMap.put("Price",Price);

                db.collection("Food Menu").document(Fname).set(foodMap);

                Toast.makeText(foodActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(foodActivity.this,foodActivity.class);
                startActivity(intent);
            }
        });
    }
}
