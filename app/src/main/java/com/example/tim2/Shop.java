package com.example.tim2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.VISIBLE;

public class Shop extends AppCompatActivity {
    String username;
    String shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        shopName = extras.getString("shopName");
        setTitle("Products");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        ContentValues c = new ContentValues();

        System.out.println(shopName);
        c.put("shopName",  shopName);
        LinearLayout holder = findViewById(R.id.productHolder);
        displayItems(holder, c);


    }

    @SuppressLint("StaticFieldLeak")
    public void displayItems(final LinearLayout holder, ContentValues cv){

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/t.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {

                    JSONArray shops = new JSONArray(output);

                    System.out.println(shops);
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        String tester = shop.toString();
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.product, null);

                        ((TextView) v.findViewById(R.id.displayedProduct)).setText(""+shop.getString("productName"));

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                               final String tempName=    ((TextView) v.findViewById(R.id.displayedProduct)).getText().toString();




                                goToPlaceOrder( tempName);



                               /*

                                Dialog dialog = new Dialog(v.getContext());
                                dialog.setContentView(R.layout.place_order_dialog);
                                ((TextView) (dialog.findViewById(R.id.productNameDialog)) ).setText(tempName);

                                dialog.show();
                                (dialog.findViewById(R.id.btnConfirmOrder)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                         View test = v.inflate(v.getContext(), R.layout.place_order_dialog, null);
                                        String text =((EditText)( test.findViewById(R.id.Order_notes))).getText().toString();

                                    }
                                });
                                */

                            }
                        });
                        holder.addView(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }



    public void goToPlaceOrder(String tempName){


        Intent intent = new Intent( Shop.this, PlaceOrder.class);
        intent.putExtra("productName",tempName);
        intent.putExtra("shopName",shopName);
        intent.putExtra("userName",username);


        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Shop.this, displayShops.class);
        intent.putExtra("username",username);
      //  intent.putExtra("shopName",shopName);
        startActivity(intent);
    }
}

