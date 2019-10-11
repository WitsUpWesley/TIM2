package com.example.tim2;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteItem extends AppCompatActivity {
    String itemName;
    String shopName;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Bundle extras = getIntent().getExtras();
        itemName = extras.getString("itemName");
        shopName = extras.getString("shopName");
        setTitle(itemName);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_item_from_product);
    }

    public void deleteItem(View v){
        /*
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
        startActivity(intent);*/
    }

}

