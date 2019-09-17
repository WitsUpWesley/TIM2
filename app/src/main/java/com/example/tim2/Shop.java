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

public class Shop extends AppCompatActivity {
    String shopName;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        shopName = extras.getString("shop");
        setTitle("Products");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        ContentValues c = new ContentValues();
        c.put("shopName", "Caves");
        LinearLayout holder = findViewById(R.id.productHolder);
        displayItems(holder, c);


    }

    public void displayItems(final LinearLayout holder, ContentValues c){

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getProductShopX.php", c) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray shops = new JSONArray(output);
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        String tester = shop.toString();
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.product, null);

                        ((TextView) v.findViewById(R.id.displayedProduct)).setText("Name: " + shop.getString("itemName"));
                        System.out.println("working");
                        holder.addView(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();

    }

}

