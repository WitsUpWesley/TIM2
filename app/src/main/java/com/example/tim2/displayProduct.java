package com.example.tim2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class displayProduct extends AppCompatActivity {
    String username,shopName;

    //@SuppressLint("StaticFieldLeak")
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Products");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.displayshops);

        username = extras.getString("username");
        shopName = extras.getString("shopName");
        System.out.println(shopName);

        ContentValues c = new ContentValues();
        c.put("shopName", shopName); // need to get this value later

        LinearLayout holder = findViewById(R.id.productHolder);

        displayProducts(holder, c);

    }

    public void displayProducts(final LinearLayout holder, ContentValues cv) {
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/t.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray shops = new JSONArray(output);
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        String tester = shop.toString();
                        System.out.println("----");
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.product, null);

                        ((TextView) v.findViewById(R.id.displayedProduct)).setText("Name: " + shop.getString("productName"));
                        final String q =((TextView) v.findViewById(R.id.displayedProduct)).getText().toString();

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(displayProduct.this, Product.class);
                                intent.putExtra("username",username);
                                intent.putExtra("shopName",shopName);
                                intent.putExtra("productName",q.substring(6));
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
}
