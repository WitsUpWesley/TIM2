package com.example.tim2;

import android.content.ContentValues;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class createProduct extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createproduct);
        setTitle("Create Product");
        Bundle extras = getIntent().getExtras();

        username = extras.getString("username");

        ContentValues cv = new ContentValues();
        cv.put("shopName","Caves");

        LinearLayout holder = findViewById(R.id.productHolder);
        EditText productName = findViewById(R.id.productName_editText);
        Button createProduct = findViewById(R.id.btnCreateProduct);

        createProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        displayItems(holder,cv);

    }

    public void onCheckBox(View view, CheckBox c){

        if(c.isChecked()){
            Toast.makeText(createProduct.this, "Checked", Toast.LENGTH_SHORT).show();
        }
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
                        View v = View.inflate(holder.getContext(), R.layout.item, null);

                        ((TextView) v.findViewById(R.id.itemShown)).setText("Name: "+ shop.getString("itemName") + "\n" +"Desc: "+ shop.get("itemDescription") + "\n" +"Quantity:" + shop.get("itemQuantity"));
                        System.out.println("working");

                       final CheckBox  c = v.findViewById(R.id.checkBox);

                        c.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                onCheckBox(v,c);
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
}
