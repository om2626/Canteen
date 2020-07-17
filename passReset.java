package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class passReset extends AppCompatActivity {

    Button Reset2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_reset);

        Reset2 = (Button) findViewById(R.id.resetBtn);

        Reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(passReset.this,MainActivity.class);
                startActivity(intent6);
            }

        });
    }


}
