package com.example.tim2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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
public class searchProduct extends AppCompatActivity{

    String username;
    String shopName;
    String productName;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        setTitle("Search Product");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchproduct);


    }

    public void displaySearchedProducts(final LinearLayout holder, ContentValues cv, final String productName) {
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/searchProduct.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray shops = new JSONArray(output);
                    if(shops.length() == 0){
                        Toast.makeText(searchProduct.this,"The product you were searching for cannot be found.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(searchProduct.this,"We have found the following products.", Toast.LENGTH_SHORT).show();
                    }
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        String tester = shop.toString();
                        System.out.println("----");
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.product, null);

                        ((TextView) v.findViewById(R.id.displayedProduct)).setText("Name: " + shop.getString("productName"));
                        shopName = shop.get("shopName")+"";
                        final String q =((TextView) v.findViewById(R.id.displayedProduct)).getText().toString();

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(searchProduct.this, PlaceOrder.class);
                                intent.putExtra("username",username);
                                intent.putExtra("shopName",shopName);
                                intent.putExtra("productName",productName);
                                startActivity(intent);

                            }
                        });
                        System.out.println("working");
                        holder.addView(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public void search(View v){
        final EditText productName = findViewById(R.id.productName_edit_text);
        String sProductName = productName.getText().toString().trim();
        System.out.println(sProductName);
        ContentValues c = new ContentValues();
        c.put("productName", sProductName); // need to get this value later

        LinearLayout holder = findViewById(R.id.productHolder1);
        displaySearchedProducts(holder,c,sProductName);

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(searchProduct.this, HomePage.class);
        intent.putExtra("username",username);
        intent.putExtra("shopName",shopName);
        startActivity(intent);
    }

}
