package com.example.tim2;


        import android.annotation.SuppressLint;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;


public class DisplayShopOrders extends AppCompatActivity {
    String username,shop;

    //@SuppressLint("StaticFieldLeak")
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("My Orders");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.orders);

        username = extras.getString("username");
        shop = extras.getString("shopName");

        ContentValues c = new ContentValues();
        c.put("shopName", shop); // need to get this value later

        LinearLayout holder = findViewById(R.id.productHolder);

        displayOrders(holder, c);

    }

    @SuppressLint("StaticFieldLeak")
    public void displayOrders(final LinearLayout holder, ContentValues cv) {

        Log.d("displayOrder","innnnnnnnnnn");
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getShopOrders.php", cv) {
            @Override
            protected void onPostExecute(final String output) {
                try {
                    final JSONArray queryResult = new JSONArray(output);

                    Log.d("displayOrder","innnnnnnnnnn2");
                    for (int i = 0; i < queryResult.length(); i++) {
                        final JSONObject orderResult = queryResult.getJSONObject(i);

                        Log.d("displayOrder","innnnnnnnnnn3");
                        View v = View.inflate(holder.getContext(), R.layout.single_shop_order, null);

                        Log.d("displayOrder","innnnnnnnnnn4");
                        ((TextView) v.findViewById(R.id.displayedOrderNo)).setText("Order Number: " + orderResult.getString("orderNo"));
                        ((TextView) v.findViewById(R.id.displayedOrder)).setText("Product: " + orderResult.getString("productName"));
                        ((TextView) v.findViewById(R.id.orderQuantityyyy)).setText("Quantity: " + orderResult.getString("quantity"));
                        ((TextView) v.findViewById(R.id.displayedStatus)).setText("Order Status: " + orderResult.getString("status"));
                        ((TextView) v.findViewById(R.id.Order_notes)).setText("Order Notes: " + orderResult.getString("orderNotes"));
                        Log.d("displayOrder","innnnnnnnnnn5");
                        holder.addView(v);

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                Log.d("displayOrder", "innnnnnnnnnn6");
                                Intent intent = new Intent(DisplayShopOrders.this, OrderManagerPopup.class);
                                String orderNum = null;
                                String orderStatus = null;

                                Log.d("displayOrder", "innnnnnnnnnn7");
                                try {
                                    orderNum = orderResult.getString("orderNo");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                intent.putExtra("orderNo", orderNum);
                                Log.d("displayOrder", "innnnnnnnnnn8");
                                try {
                                    orderStatus = orderResult.getString("status");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d("displayOrder", "innnnnnnnnnn9");
                                intent.putExtra("status", orderStatus);
                                intent.putExtra("username",username);
                                intent.putExtra("shopName",shop);
                                if(! orderStatus.equalsIgnoreCase("Completed")) {
                                // intent.putExtra("shopName", shop);
                                startActivity(intent);
                                    }else{
                                    Toast.makeText(DisplayShopOrders.this,"Order Complete.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DisplayShopOrders.this, ownShop.class);
        intent.putExtra("username",username);
       intent.putExtra("shopName",shop);
        startActivity(intent);
    }
}
