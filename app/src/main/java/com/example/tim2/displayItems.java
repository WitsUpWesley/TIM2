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

public class displayItems extends AppCompatActivity {
    String username;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Items");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.displayshops);

        username = extras.getString("username");

        ContentValues c = new ContentValues();
        c.put("shopName","Caves"); // need to get this value later

        LinearLayout holder = findViewById(R.id.shopHolder);
        //System.out.println("PROBLEM AREA");
        displayItems(holder,c);

    }

    public void displayItems(final LinearLayout holder, ContentValues cv) {

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getItemFromShopX.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray shops = new JSONArray(output);
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        String tester = shop.toString();
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.shop_item_larger, null);

                        ((TextView) v.findViewById(R.id.displayedItem)).setText("Name: "+ shop.getString("itemName") + "\n" +"Desc: "+ shop.get("itemDescription") + "\n" +"Quantity:" + shop.get("itemQuantity"));
                        System.out.println("working");
                        /*final String q =((TextView) v.findViewById(R.id.displayedShop)).getText().toString();
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                openViewAnswersPage(q);

                            }
                        });*/

                        holder.addView(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}

