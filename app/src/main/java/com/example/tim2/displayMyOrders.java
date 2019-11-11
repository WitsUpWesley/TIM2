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


public class displayMyOrders extends AppCompatActivity {
    String username;

    //@SuppressLint("StaticFieldLeak")
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("My Orders");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        setContentView(R.layout.orders);

        username = extras.getString("username");


        ContentValues c = new ContentValues();
        c.put("username", "a"); // need to get this value later

        LinearLayout holder = findViewById(R.id.productHolder);

        displayOrders(holder, c);

    }

    @SuppressLint("StaticFieldLeak")
    public void displayOrders(final LinearLayout holder, ContentValues cv) {

        Log.d("displayOrder","in");
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/getMyOrders.php", cv) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray queryResult = new JSONArray(output);


                    Log.d("displayOrder","in2"+queryResult.toString());



                    for (int i = 0; i < queryResult.length(); i++) {
                        final JSONObject orderResult = queryResult.getJSONObject(i);
                        String tester = orderResult.toString();
                        System.out.println("----");
                        System.out.println(tester);
                        View v = View.inflate(holder.getContext(), R.layout.single_order, null);


                        Log.d("displayOrder","in3");

                        ((TextView) v.findViewById(R.id.displayedOrderNo)).setText("Order Number: " + orderResult.getString("orderNo"));
                        ((TextView) v.findViewById(R.id.displayedOrder)).setText("Product: " + orderResult.getString("productName"));

                        ((TextView) v.findViewById(R.id.orderQuantityy)).setText("Quantity: " + orderResult.getString("quantity"));




                        ((TextView) v.findViewById(R.id.orderStatus)).setText("Order Status: " + orderResult.getString("status"));

                        ((TextView) v.findViewById(R.id.orderNotess)).setText("Order Notes: " + orderResult.getString("orderNotes"));


                       /* v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(displayMyOrders.this, Product.class);
                                intent.putExtra("username",username);
                                intent.putExtra("shopName",shopName);
                                intent.putExtra("productName",q.substring(6));
                                startActivity(intent);

                            }
                        });*/
                        System.out.println("working");
                        holder.addView(v);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(displayMyOrders.this, HomePage.class);
        intent.putExtra("username",username);
      //  intent.putExtra("shopName",shopName);
        startActivity(intent);
    }
}
