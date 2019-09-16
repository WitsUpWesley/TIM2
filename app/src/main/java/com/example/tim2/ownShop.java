package com.example.tim2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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

import static android.view.View.VISIBLE;

public class ownShop extends AppCompatActivity {
    String username;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Your shop"); // get it from a query maybe
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownshop);

        Button createProductButton = findViewById(R.id.btnCreateProduct);

        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ownShop.this, createProduct.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


    }

    public void createItem(View v){
        Intent intent = new Intent( ownShop.this, createItem.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void viewItems(View v){
        Intent intent = new Intent( ownShop.this, displayItems.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

}


