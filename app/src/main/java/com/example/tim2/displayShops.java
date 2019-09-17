package com.example.tim2;

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

public class displayShops extends AppCompatActivity {
    String username;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Shops");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.displayshops);

        username = extras.getString("username");

        ContentValues c = new ContentValues();

        LinearLayout holder = findViewById(R.id.productHolder);
        System.out.println("PROBLEM AREA");
        displayShops(holder,c);

    }

    public void displayShops(final LinearLayout holder, ContentValues cv) {

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShops.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray shops = new JSONArray(output);
                    for (int i = 0; i < shops.length(); i++) {
                        final JSONObject shop = shops.getJSONObject(i);
                        View v = View.inflate(holder.getContext(), R.layout.shop_item, null);

                        ((TextView) v.findViewById(R.id.displayedShop)).setText(shop.getString("Shop Name"));

                 final String q =((TextView) v.findViewById(R.id.displayedShop)).getText().toString();
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(displayShops.this, Shop.class);
                                intent.putExtra("username",username);
                                intent.putExtra("shopName",q);
                                startActivity(intent);

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

    private void openViewAnswersPage(String s){

        Intent viewAnswersIntent = new Intent(this, Shop.class);
        viewAnswersIntent.putExtra("shop", s);
        startActivity(viewAnswersIntent);
    }

}

