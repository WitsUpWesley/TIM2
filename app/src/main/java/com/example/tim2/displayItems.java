package com.example.tim2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.view_items);

        username = extras.getString("username");
        shopName = extras.getString("shopName");

        ContentValues c = new ContentValues();
        c.put("shopName", shopName); // need to get this value later

        LinearLayout holder = findViewById(R.id.itemHolder);
        //System.out.println("PROBLEM AREA");
        displayItems(holder, c);

        Button updateButton = findViewById(R.id.btnUpdateItemQuantity);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put("shopName", shopName);

                new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getItemFromShopX.php", cv) {

                    EditText itemNameEditText = findViewById(R.id.itemName_editText);
                    final String itemName = itemNameEditText.getText().toString().trim();


                    EditText itemQuantityEditText = findViewById(R.id.itemQuantity_editText);
                    String itemQuantity = itemQuantityEditText.getText().toString().trim();

                    @Override
                    protected void onPostExecute(String output) {
                        try {
                            JSONArray shops = new JSONArray(output);
                            for (int i = 0; i < shops.length(); i++) {
                                final JSONObject shop = shops.getJSONObject(i);
                                if(shop.getString("itemName").equals(itemName)){
                                    String tester = shop.getString("itemQuantity");
                                    int prevQuantity = Integer.parseInt(tester);
                                    int total = prevQuantity + Integer.parseInt(itemQuantity);

                                    update(shopName, itemName, Integer.toString(total));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();



            }
        });

    }

    public void update(String shopName, String itemName, String itemQuantity){

        ContentValues cv = new ContentValues();
        cv.put("shopName", shopName);
        cv.put("itemName", itemName);
        cv.put("itemQuantity", itemQuantity);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/updateItemQuantity.php", cv) {
            @Override
            protected void onPostExecute(String output){

                        Toast.makeText(displayItems.this,"Successfully Updated", Toast.LENGTH_SHORT).show();

            }
        }.execute();


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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

}
