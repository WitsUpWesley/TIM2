package com.example.tim2;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.Object;

public class createItem extends AppCompatActivity {
    String username;

    //@SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Create item");
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createitem);
    }

    public void createTheItem(View v){
        final EditText shopName = findViewById(R.id.txtShopName),
                itemName = findViewById(R.id.txtItemName),
                itemDesc = findViewById(R.id.txtItemDesc),
                quantity = findViewById(R.id.txtQuantity);

        String sShopName = shopName.getText().toString().trim();
        String sItemName = itemName.getText().toString().trim();
        String sItemDesc = itemDesc.getText().toString().trim();
        String sQuantity = quantity.getText().toString().trim();

        if(sShopName.isEmpty() || sItemName.isEmpty() || sItemDesc.isEmpty() || sQuantity.isEmpty()){
            Toast.makeText(createItem.this, "An error has occured please try again", Toast.LENGTH_SHORT).show();
        }
        else{
            int iQuantity = Integer.parseInt(sQuantity);
            ContentValues cv = new ContentValues();
            cv.put("shopName",sShopName);
            cv.put("itemName", sItemName);
            cv.put("Description",sItemDesc);
            cv.put("itemQuantity",iQuantity);
            addItem(cv);
            shopName.getText().clear();
            itemDesc.getText().clear();
            itemName.getText().clear();
            quantity.getText().clear();
            Toast.makeText(createItem.this, "The item has been added", Toast.LENGTH_SHORT).show();
        }


    }

    public static void addItem(ContentValues cv){
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/addItem.php", cv) {
            @Override
            protected void onPostExecute(String output) {
            }
        }.execute();
    }
}
