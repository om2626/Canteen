package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    private Button Signup,Login,Upload;
    private TextView Reset;
    private EditText userEmail,userPass;
    private FirebaseFirestore db;
    public static final String EXTRA_NAME = "com.example.canteenapp.EXTRA_NAME";
    public static final String EXTRA_PASS = "com.example.canteenapp.EXTRA_PASS";
    String eid,epass,email,pass,perm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    void loginAct(View view)
    {
        db = FirebaseFirestore.getInstance();
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPass);
        Login = findViewById(R.id.LoginBtn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = userEmail.getText().toString();
                pass = userPass.getText().toString();

                db.collection("Users").document(email).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                eid = documentSnapshot.getString("Username");
                                epass = documentSnapshot.getString("Password");
                                perm = documentSnapshot.getString("Permission");
                                if(!email.isEmpty() && !pass.isEmpty()) {
                                    if (email.equals(eid) && pass.equals(epass)) {

                                        if(perm.equals("Standard User")) {
                                            Intent intent = new Intent(MainActivity.this, mainMenu.class);
                                            intent.putExtra(EXTRA_NAME, eid);
                                            intent.putExtra(EXTRA_PASS, epass);
                                            startActivity(intent);
                                        }
                                        else if(perm.equals("Admin")){
                                            Intent intent2 = new Intent(MainActivity.this,AdminPage.class);
                                            startActivity(intent2);
                                        }
                                    }
                                }
                                else {
                                    Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(intent2);
                                    Toast.makeText(MainActivity.this, "Email / Password Fields empty", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


    void signupAct(View view)
    {
        Intent intent2 = new Intent(MainActivity.this,signup.class);
        startActivity(intent2);
    }

    void resetAct(View view)
    {
        Intent intent5 = new Intent(MainActivity.this,passReset.class);
        startActivity(intent5);
    }

    void foodAct(View view)
    {
        Intent intent = new Intent(MainActivity.this,foodActivity.class);
        startActivity(intent);
    }


}




