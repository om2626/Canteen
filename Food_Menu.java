package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Food_Menu extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference dbRef;
    private Button choiceBtn2;
    private EditText etChoice2;
    private TextView  tvFname2, tvCalorie2, tvPrice2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__menu);

        db = FirebaseFirestore.getInstance();
        dbRef = db.collection("Food Menu");
        tvFname2 = findViewById(R.id.tvFname2);
        tvCalorie2 = findViewById(R.id.tvCalorie);
        tvPrice2 = findViewById(R.id.tvPrice2);

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
                            data1 += (i++) + "    " + Fname2 + "\n";
                            data2 += Calorie2 + "\n";
                            data3 += Price2 + "\n";
                            tvFname2.setText(data1);
                            tvCalorie2.setText(data2);
                            tvPrice2.setText(data3);
                        }
                    }
                });
    }
}
