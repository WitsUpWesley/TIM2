
package com.example.tim2;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.DisplayMetrics;
        import android.util.Log;
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

public class OrderManagerPopup extends Activity {
    String shopName,status, orderNo;
    String username,shop;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shop_order_manager);

        final Bundle extras = getIntent().getExtras();
       // shopName = extras.getString("shopName");
        status = extras.getString("status");
        orderNo = extras.getString("orderNo");
        Log.d("displayOrder","innnnnnoutputnnnnn1");
        username = extras.getString("username");
        shop = extras.getString("shopName");

        Button nextStage = findViewById(R.id.btnNextStage);


        nextStage.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put("status", status);
                cv.put("orderNo", orderNo);
                Log.d("displayOrder","innnnnnnnnnn4");

                updateStats();
                finish();
            }
        });
        Button cacelbtn = findViewById(R.id.btnCancelOrder);
        cacelbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put("status", status);
                cv.put("orderNo", orderNo);
                Log.d("displayOrder","innnnnnnnnnn4");

                cancelOrder();
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


 public void updateStats(){
     ContentValues cv = new ContentValues();
     Log.d("displayOrder","tired to update the status1");


     if(status.equalsIgnoreCase("Cancelled")){
         status= "Processing";
     }else
     if(status.equalsIgnoreCase("Processing")){
         status= "Accepted";
     }else if(status.equalsIgnoreCase("Accepted")){
         status= "Shipped";
     }else if(status.equalsIgnoreCase("Shipped")){
         status= "Completed";
     }
     cv.put("status", status);
     cv.put("orderNo", orderNo);
     Log.d("displayOrder","tired to update the status2");
     new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/updateOrderStatus.php", cv) {

         @Override
         protected void onPostExecute(String output) {
             try {
                // JSONArray shops = new JSONArray(output);
    //need to cvheck if succesful then do this and refresh screen also if order cvomplete cant go to this page.


                 Log.d("displayOrder","tired to update the statusssssssssss"+output);


             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }.execute();

     Toast.makeText(OrderManagerPopup.this,"Update success.", Toast.LENGTH_SHORT).show();

     Intent intent = new Intent(OrderManagerPopup.this, DisplayShopOrders.class);
     intent.putExtra("username",username);
     intent.putExtra("shopName",shop);
     startActivity(intent);



 }
 @SuppressLint("StaticFieldLeak")
    public void cancelOrder() {

     if (status.equalsIgnoreCase("Complete")) {
         Toast.makeText(OrderManagerPopup.this, "Order is already complete.", Toast.LENGTH_SHORT).show();

         Intent intent = new Intent(OrderManagerPopup.this, DisplayShopOrders.class);
         intent.putExtra("username", username);
         intent.putExtra("shopName", shop);
         startActivity(intent);
     } else {
         ContentValues cv = new ContentValues();

         status = "Cancelled";

         cv.put("status", status);
         cv.put("orderNo", orderNo);
         Log.d("displayOrder", "tired to update the status2");
         new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/updateOrderStatus.php", cv) {
             @Override
             protected void onPostExecute(String output) {
                 try {
                     // JSONArray shops = new JSONArray(output);
                     //need to cvheck if succesful then do this and refresh screen also if order cvomplete cant go to this page.


                     Log.d("displayOrder", "tired to update the 44444444444ss" + output);


                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }.execute();

         Toast.makeText(OrderManagerPopup.this, "Update success.", Toast.LENGTH_SHORT).show();

         Intent intent = new Intent(OrderManagerPopup.this, DisplayShopOrders.class);
         intent.putExtra("username", username);
         intent.putExtra("shopName", shop);
         startActivity(intent);
     }
 }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderManagerPopup.this, DisplayShopOrders.class);
        intent.putExtra("username",username);
        intent.putExtra("shopName",shop);
        startActivity(intent);
    }



}
