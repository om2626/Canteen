package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Suggession extends AppCompatActivity {

    private TextView tvBmi, tvStatus, tvFname, tvCalorie, tvPrice;
    private FirebaseFirestore db;
    private CollectionReference dbRef;
    private int bmi, range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggession2);

        //BMI = Weight / ((Height / 100)^2)
        Intent intent = getIntent();
        db = FirebaseFirestore.getInstance();
        tvBmi = findViewById(R.id.tvBmi);
        tvFname = findViewById(R.id.tvFname);
        tvCalorie = findViewById(R.id.tvCalorie2);
        tvPrice = findViewById(R.id.tvPrice);
        tvStatus = findViewById(R.id.tvStatus);
        dbRef = db.collection("Food Menu");
        String eid = intent.getStringExtra(MainActivity.EXTRA_NAME);

        db.collection("Users").document(eid)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                int height = Integer.parseInt(documentSnapshot.getString("Height"));
                int weight = Integer.parseInt(documentSnapshot.getString("Weight"));
                bmi = weight / ((height/100)^2);
                String BMI = Float.toString(bmi);
                tvBmi.setText("BMI : " + BMI);
                if(bmi <= 18.0) {
                    tvStatus.setText("You are Underweight");
                    range = 300;
                    Retrieve();
                }
                else if(bmi > 18 && bmi <= 25) {
                    tvStatus.setText("You are Normal");
                    range = 250;
                    Retrieve();
                }
                else if(bmi > 25 && bmi <= 29.9) {
                    tvStatus.setText("You are Overweight");
                    range = 200;
                    Retrieve();
                }
                else if(bmi > 29.9) {
                    tvStatus.setText("You are Obese");
                    range = 100;
                    Retrieve();
                }

            }
        });

    }

    void Retrieve()
    {


        dbRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data1 = "";
                        String data2 = "";
                        String data3 = "";
                        int i = 1;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                        {
                            String Fname2 = documentSnapshot.getString("Food Name");
                            String Calorie2 = documentSnapshot.getString("Calorie");
                            String Price2 = documentSnapshot.getString("Price");

                            //data=Calorie2;
                            int Cal = Integer.parseInt(Calorie2);
                            tvFname.setText(Integer.toString(Cal));
                            if(Cal < range) {
                                data1 += (i++) + "    " + Fname2 + "\n";
                                data2 += Cal + "\n";
                                data3 += Price2 + "\n";
                            }
                            tvFname.setText(data1);
                            tvCalorie.setText(data2);
                            tvPrice.setText(data3);
                        }
                    }
                });
    }
}
