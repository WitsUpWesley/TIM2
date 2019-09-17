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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.VISIBLE;

public class ownShop extends AppCompatActivity {
    String username,shopName;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();

        username = extras.getString("username");
        shopName = extras.getString("shopName");
        setTitle(shopName); // get it from a query maybe
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
        ContentValues cv = new ContentValues();

        cv.put("owner", username);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShopFromOwner.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                Intent intent = new Intent( ownShop.this, displayItems.class);
                try{
                    JSONArray shops = new JSONArray(output);
                    final JSONObject shop = shops.getJSONObject(0);
                    String shopName = shop.getString("Shop Name");
                    intent.putExtra("shopName", shopName);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                intent.putExtra("username",username);
                startActivity(intent);
            }
        }.execute();
    }

}


