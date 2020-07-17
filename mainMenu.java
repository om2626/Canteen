package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mainMenu extends AppCompatActivity {

    private Button Settings, Suggest, Menu;
    private String eid,epass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        final Intent intent = getIntent();
        eid = intent.getStringExtra(MainActivity.EXTRA_NAME);
        epass = intent.getStringExtra(MainActivity.EXTRA_PASS);
        Settings = (Button) findViewById(R.id.settingBtn);
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(mainMenu.this,settingsPage.class);
                intent7.putExtra(MainActivity.EXTRA_NAME,eid);
                intent7.putExtra(MainActivity.EXTRA_PASS,epass);
                startActivity(intent7);
            }

        });

        Suggest = (Button) findViewById(R.id.Suggession_menu);
        Suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent8 = new Intent(mainMenu.this,Suggession.class);
                intent8.putExtra(MainActivity.EXTRA_NAME,eid);
                startActivity(intent8);
            }

        });

        Menu = findViewById(R.id.foodMenu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent10 = new Intent(mainMenu.this,Food_Menu.class);
                startActivity(intent10);
            }

        });
    }
}
