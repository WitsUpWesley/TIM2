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

import static android.view.View.VISIBLE;

public class createShop extends AppCompatActivity {
    String username;
    boolean existingShop = false;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Create Shop");
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        System.out.println(username);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createshop);




        ContentValues cv = new ContentValues();
        cv.put("owner",username);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShopFromOwner.php", cv) {
            @Override
            protected void onPostExecute(String output)
            {
                try {
                    JSONArray shops = new JSONArray(output);
                    if(shops.length() > 0){
                        existingShop = true;
                        System.out.println("There is a shop so you can't make one");
                    }
                    else{
                        System.out.println("make a shop");
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

    public void createTheShop(View v){
        final EditText shopName = findViewById(R.id.txtName),
                shopDesc = findViewById(R.id.txtDesc),
                shopType = findViewById(R.id.txtType);

        String sShopName = shopName.getText().toString().trim();
        String sShopDesc = shopDesc.getText().toString().trim();
        String sShopType = shopType.getText().toString().trim();

        if(sShopName.isEmpty() || sShopDesc.isEmpty() || sShopType.isEmpty()){
            Toast.makeText(createShop.this, "An error has occured please try again", Toast.LENGTH_SHORT).show();
        }
        else{
            final ContentValues c = new ContentValues();
            c.put("shopName",sShopName);
            c.put("shopDescription",sShopDesc);
            c.put("type",sShopType);
            c.put("owner",username);
            if(existingShop == true){
                Toast.makeText(createShop.this, "You already own a shop and cannot make another.", Toast.LENGTH_SHORT).show();
                shopName.getText().clear();
                shopDesc.getText().clear();
                shopType.getText().clear();
            }
            else{
                addShop(c);
                shopName.getText().clear();
                shopDesc.getText().clear();
                shopType.getText().clear();
                Toast.makeText(createShop.this, "The item has been added", Toast.LENGTH_SHORT).show();
            }

        }


    }

    public static void addShop(ContentValues c){
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/addShop.php", c) {
            @Override
            protected void onPostExecute(String output) {
            }
        }.execute();
    }

}


