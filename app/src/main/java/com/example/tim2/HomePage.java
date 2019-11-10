package com.example.tim2;

import android.annotation.SuppressLint;
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
        final ContentValues cv = new ContentValues();
        cv.put("owner",username);
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShopFromOwner.php", cv) {
            @Override
            protected void onPostExecute(String output)
            {
                try {
                 // cv.clear();

                    JSONArray shops = new JSONArray(output);
                    if(shops.length() > 0){
                    }
                    else{
                        Button btnTemp = findViewById(R.id.btnViewOwnShop);
                        btnTemp.setVisibility(View.INVISIBLE);

                    }
                    //final JSONObject shop = shops.getJSONObject(0);
                    //System.out.println("You can create a shop");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
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
        ContentValues cv = new ContentValues();

        cv.put("owner", username);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShopFromOwner.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                Intent intent = new Intent( HomePage.this, createShop.class);
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

    public void viewOwnShop(View v){

        ContentValues cv = new ContentValues();

        cv.put("owner", username);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShopFromOwner.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                Intent intent = new Intent( HomePage.this, ownShop.class);
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

    public void searchProduct(View v){
        Intent intent = new Intent( HomePage.this, searchProduct.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}

