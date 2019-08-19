package com.example.tim2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_in_page);

        final EditText
                username = findViewById(R.id.username_editText),
                password = findViewById(R.id.password_editText);

        Button login = findViewById(R.id.login_button),
                signup = findViewById(R.id.signup_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sUser = username.getText().toString().trim();
                String sPass = password.getText().toString().trim();
                login(LogInPage.this, sUser, sPass);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInPage.this, SignUp.class));
            }
        });
    }

    public static void login(final Context c , String username, String password){
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("password", password);

        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/login.php", cv) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("1")){
                    Toast.makeText(c, "Successful", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();

    }
}
