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


public class signup extends AppCompatActivity {

    private Button Signup2;
    private EditText userPass, userPassConf;
    public EditText userEmail;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = FirebaseFirestore.getInstance();

        userEmail = (EditText)findViewById(R.id.userEmail);
        userPass = (EditText)findViewById(R.id.userPass);
        userPassConf = (EditText)findViewById(R.id.userPassConf);

        Signup2 = (Button) findViewById(R.id.signUpBtn2);

        Signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email = userEmail.getText().toString().trim();
                String user_pass = userPass.getText().toString().trim();
                String user_pass_con = userPassConf.getText().toString().trim();

                if(!(user_email.isEmpty()) && !(user_pass.isEmpty()) && !(user_pass_con).isEmpty())
                {
                    if(user_pass.equals(user_pass_con))
                    {

                        Map<String, String> userMap = new HashMap<>();

                        userMap.put("Username",user_email);
                        userMap.put("Password",user_pass);
                        userMap.put("Permission","Standard User");

                        db.collection("Users").document(user_email).set(userMap);

                        Toast.makeText(signup.this,"User Added",Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(signup.this,MainActivity.class);
                        startActivity(intent3);
                    }
                }
                else
                {
                    Intent intent4 = new Intent(signup.this,signup.class);
                    startActivity(intent4);
                    Toast.makeText(signup.this,"Email / Password Fields empty",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}