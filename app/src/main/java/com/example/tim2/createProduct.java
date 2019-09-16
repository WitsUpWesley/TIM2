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

import java.util.ArrayList;

public class createProduct extends AppCompatActivity {
    String username;
    String proName;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> numItems = new ArrayList<>();

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

        Button createProduct = findViewById(R.id.btnCreateProduct);

        createProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText productName = findViewById(R.id.productName_editText);
                proName = productName.getText().toString().trim();
               // numItems.remove("");

                for(int i = 0; i < items.size(); i++){

                    final ContentValues cont = new ContentValues();
                    int itemQuantity = 6;

                    cont.put("shopName","Caves");
                    cont.put("productName", proName);
                    cont.put("numItemsNeeded", itemQuantity);
                    cont.put("itemName", items.get(i));
                    addProduct(items, cont);

                    //System.out.println(numItems.get(i));
                }


            }
        });

        displayItems(holder,cv);

    }

    public void onCheckBox(View view, CheckBox c, String itemadded,String numItemsNeeded){

        if(c.isChecked()){
            items.add(itemadded);
            numItems.add(numItemsNeeded);

        }

        else if(!c.isChecked()){
            items.remove(itemadded);
            numItems.remove(numItemsNeeded);
        }
    }

    public void addProduct(ArrayList items, ContentValues cont){

                new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/addProduct.php", cont) {
                    @Override
                    protected void onPostExecute(String output) {

                        if(output.equals("1")){
                            Toast.makeText(createProduct.this,"Product added", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(createProduct.this,"Failed", Toast.LENGTH_SHORT).show();
                        }
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
                        final View v = View.inflate(holder.getContext(), R.layout.item, null);

                        ((TextView) v.findViewById(R.id.itemShown)).setText("Name: "+ shop.getString("itemName") + "\n" +"Desc: "+ shop.get("itemDescription") + "\n" +"Quantity:" + shop.get("itemQuantity"));
                        System.out.println("working");

                        final String itemadded = shop.getString("itemName");

                        final CheckBox  c = v.findViewById(R.id.checkBox);

                        c.setOnClickListener(new View.OnClickListener() {

                            EditText itemsNeeded = v.findViewById(R.id.itemsNeeded_editText);

                            final String numItemsNeeded = itemsNeeded.getText().toString().trim();

                            @Override
                            public void onClick(View v) {

                                onCheckBox(v,c,itemadded,numItemsNeeded);

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
