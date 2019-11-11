package com.example.tim2;
        import android.annotation.SuppressLint;
        import android.app.Dialog;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
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

public class PlaceOrder extends AppCompatActivity {
    String username;
    String shopName;
    String productName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        username = extras.getString("userName");
        shopName = extras.getString("shopName");
        productName= extras.getString("productName");



        setTitle("Place An Order");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_dialog);

       // ContentValues c = new ContentValues();


      //  c.put("productName",  shopName);

        ((TextView) (findViewById(R.id.productNameDialog)) ).setText(productName);
     //   ((TextView) (findViewById(R.id.productNameDialog)) ).setText(productName);

      //  LinearLayout holder = findViewById(R.id.productHolder);
      //  displayItems(holder, c);


    }





    public void placeOrder(View v){

try{
    int quan= Integer.parseInt(((TextView) (findViewById(R.id.orderQuantityyyy)) ).getText().toString());
    // Log.d("PalceOrder",((EditText) (findViewById(R.id.orderQuantityyyy)) ).getText().toString());
       Log.d("PalceOrder",quan+"");

     String notes= ((TextView) (findViewById(R.id.Order_notes)) ).getText().toString();

//int quan=-1;
       // String notes="";
        if(quan>0) {
            Log.d("PalceOrder","usrname:"+username);
            Log.d("PalceOrder","inasyc");
            ContentValues cv = new ContentValues();
            cv.put( "userName",username );
            cv.put( "shopName",shopName );
            cv.put( "productName",productName );
            cv.put( "quantity",quan );
            cv.put( "orderNotes", notes);
            //INSERT INTO `tblOrders`( `userName`, `shopName`, `productName`, `quantity`,  `orderNotes`) VALUES (,,,,)
            new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/placeOrder.php", cv) {

                    @Override
                    protected void onPostExecute(String output)
                    {
                        Log.d("PalceOrder","inasyc1");
                        try {
                         //   JSONArray shops = new JSONArray(output);
                            Log.d("PalceOrder",output);

                          //  String temp = shops.getString(0);
                            Log.d("PalceOrder","inasyc2");
                            if(output.equals("0")){
                                Toast.makeText(PlaceOrder.this, "Error: Order Placed unsuccessful try again later.", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(PlaceOrder.this, "Order placed successfully.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(PlaceOrder.this, Shop.class);
                                intent.putExtra("username",username);
                                intent.putExtra("shopName",shopName);
                                startActivity(intent);
                            }

                        }
                        catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }.execute();


        }else{
         Toast.makeText(PlaceOrder.this, "Quantity ordered must be at least one.", Toast.LENGTH_LONG).show();
        }
    }catch (Exception e){
        Toast.makeText(PlaceOrder.this, "Please make sure order quantity is not empty.", Toast.LENGTH_LONG).show();

    }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlaceOrder.this, HomePage.class);
        intent.putExtra("username",username);
        intent.putExtra("shopName",shopName);
        startActivity(intent);
    }


}

