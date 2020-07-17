package com.example.canteenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    private Button Add, Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
    }

    void FoodAct(View view)
    {
        Add = findViewById(R.id.addFood);
        Intent intent1 = new Intent(AdminPage.this, foodActivity.class);
        startActivity(intent1);
    }

    void DeleteAct(View view)
    {
        Delete = findViewById(R.id.deleteFood);
        Intent intent2 = new Intent(AdminPage.this, foodDelete.class);
        startActivity(intent2);
    }
}
