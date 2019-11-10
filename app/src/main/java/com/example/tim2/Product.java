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

public class Product extends AppCompatActivity {
    String username;
    String shopName;
    String productName;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        shopName = extras.getString("shopName");
        productName = extras.getString("productName");
        setTitle(productName);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayproduct);

        ContentValues c = new ContentValues();

        System.out.println(shopName);
        c.put("shopName",shopName);
        c.put("productName",productName);
        LinearLayout holder = findViewById(R.id.productHolder1);
        displayItems(holder, c);
    }

    public void displayItems(final LinearLayout holder, ContentValues cv) {

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getItemFromProductShopX.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray shops = new JSONArray(output);
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        String tester = shop.toString();
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.shop_item_larger, null);
                        //if(shop.getString("shopName") == shopName){
                        ((TextView) v.findViewById(R.id.displayedItem)).setText("Name: " + shop.getString("itemName") + "\n" + "Quantity Needed: " + shop.get("numItemsNeeded"));
                        final String q =shop.getString("itemName");
                        //}
                        /* REPLACE WITH ORDERING CODE OR WHATEVER

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Product.this, DeleteItem.class);
                                intent.putExtra("shopName",shopName);
                                intent.putExtra("itemName",q);
                                intent.putExtra("productName",productName);
                                startActivity(intent);
                            }
                        });

                        */
                        System.out.println("working");

                        holder.addView(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public void deleteProduct(View v){
        ContentValues cv = new ContentValues();
        cv.put("shopName",shopName);
        cv.put("productName",productName);
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/deleteProduct.php",cv) {
            @Override
            protected void onPostExecute(String output) {
                    Toast.makeText(Product.this,"Product deleted.", Toast.LENGTH_SHORT).show();
            }
        }.execute();
        Intent intent = new Intent(Product.this, displayProduct.class);
        intent.putExtra("username", username);
        intent.putExtra("shopName", shopName);
        startActivity(intent);
    }

}

