package com.example.tim2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class displayItems extends AppCompatActivity {
    String username,shopName;

    //@SuppressLint("StaticFieldLeak")
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Items");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_items);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        shopName = extras.getString("shopName");

        ContentValues c = new ContentValues();
        c.put("shopName", shopName);

        LinearLayout holder = findViewById(R.id.itemHolder);
        displayItems(holder, c);

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
                        ((TextView) v.findViewById(R.id.displayedItem)).setText("Name: " + shop.getString("itemName") + "\n" + "Desc: " + shop.get("itemDescription") + "\n" + "Quantity:" + shop.get("itemQuantity"));
                        System.out.println("working");
                        holder.addView(v);

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(displayItems.this, pop.class);
                                String itemName = null;
                                try {
                                    itemName = shop.getString("itemName");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                intent.putExtra("itemName", itemName);
                                intent.putExtra("shopName", shopName);
                                intent.putExtra("username", username);
                                startActivity(intent);

                            }

                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

}
