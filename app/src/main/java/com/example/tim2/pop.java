package com.example.tim2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pop extends Activity{
    String shopName, itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);
        final Bundle extras = getIntent().getExtras();

        shopName = extras.getString("shopName");
        itemName = extras.getString("itemName");

        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        itemNameTextView.setText(itemName);

        Button update = findViewById(R.id.updateBotton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put("shopName", shopName);

                new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getItemFromShopX.php", cv) {

                    EditText itemQuantityEditText = findViewById(R.id.itemQuantityEditText);
                    final String itemQuantity = itemQuantityEditText.getText().toString().trim();

                    @Override
                    protected void onPostExecute(String output) {
                        try {
                            JSONArray shops = new JSONArray(output);
                            for (int i = 0; i < shops.length(); i++) {
                                final JSONObject shop = shops.getJSONObject(i);
                                if (shop.getString("itemName").equals(itemName)) {
                                    String tester = shop.getString("itemQuantity");
                                    int prevQuantity = Integer.parseInt(tester);
                                    int total = prevQuantity + Integer.parseInt(itemQuantity);

                                    if(itemQuantity != null){
                                        update(shopName, itemName, Integer.toString(total));
                                    }

                                    else if(itemQuantity == ""){

                                        Toast.makeText(pop.this,"Missing field", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();

               finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.3));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }

    public void update(String shopName, String itemName, String itemQuantity){

        ContentValues cv = new ContentValues();
        cv.put("shopName", shopName);
        cv.put("itemName", itemName);
        cv.put("itemQuantity", itemQuantity);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/updateItemQuantity.php", cv) {
            @Override
            protected void onPostExecute(String output){

                Toast.makeText(pop.this,"Successfully Updated", Toast.LENGTH_SHORT).show();

            }
        }.execute();

    }
}
