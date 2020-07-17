package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class settingsPage extends AppCompatActivity {
    private Button Save;
    private EditText etName,etAge,etHieght,etWieght;
    private RadioGroup rgGender;
    private RadioButton radioBtn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etHieght = (EditText) findViewById(R.id.etHieght);
        etWieght = (EditText) findViewById(R.id.etWieght);
        db = FirebaseFirestore.getInstance();

        Save = (Button) findViewById(R.id.saveBtn);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String gender = ((RadioButton)findViewById(rgGender.getCheckedRadioButtonId())).getText().toString();
                Intent intent = getIntent();
                String Name = etName.getText().toString().trim();
                String Age = etAge.getText().toString().trim();
                String Height = etHieght.getText().toString().trim();
                String Weight = etWieght.getText().toString().trim();
                int RadioId = rgGender.getCheckedRadioButtonId();
                radioBtn = findViewById(RadioId);
                String Gender = radioBtn.getText().toString();

                String eid = intent.getStringExtra(MainActivity.EXTRA_NAME);
                String epass = intent.getStringExtra(MainActivity.EXTRA_PASS);
                String Perm = "Standard User";
                Map<String, String> userDetailsMap = new HashMap<>();
                userDetailsMap.put("Name",Name);
                userDetailsMap.put("Age",Age);
                userDetailsMap.put("Height",Height);
                userDetailsMap.put("Weight",Weight);
                userDetailsMap.put("Gender",Gender);
                userDetailsMap.put("Username",eid);
                userDetailsMap.put("Password",epass);
                userDetailsMap.put("Permission",Perm);

                //if(!Name.isEmpty() && !Age.isEmpty() && !Height.isEmpty() && !Weight.isEmpty() && Gender.isEmpty()) {
                    db.collection("Users").document(eid).set(userDetailsMap);
                    Intent intent2 = new Intent(settingsPage.this, mainMenu.class);
                    startActivity(intent2);

                /*else{
                    Intent intent1 = new Intent(settingsPage.this, settingsPage.class);
                    startActivity(intent1);
                    Toast.makeText(settingsPage.this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
                }*/
                }

        });
    }
}
