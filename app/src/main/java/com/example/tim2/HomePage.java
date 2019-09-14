package com.example.tim2;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            username = getIntent().getExtras().getString("username");
        }
        setTitle("Home");
    }


    public void logout(View v) {
        Intent intent = new Intent(HomePage.this, LogInPage.class);
        startActivity(intent);
    }

    public void viewShops(View v){
        Intent intent = new Intent( HomePage.this, displayShops.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void createShop(View v){
        Intent intent = new Intent( HomePage.this, createShop.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void viewOwnShop(View v){
        Intent intent = new Intent( HomePage.this, ownShop.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}

