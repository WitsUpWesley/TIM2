package com.example.tim2;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        final EditText

                eUsername = findViewById(R.id.enterUsername_editText),
                ePassword = findViewById(R.id.enterPassword_editText),
                cPassword = findViewById(R.id.confirmPassword_editText);

        Button create = findViewById(R.id.create_button);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = eUsername.getText().toString().trim();
                String pWord = ePassword.getText().toString().trim();
                String cpWprd = cPassword.getText().toString().trim();

                ContentValues cv = new ContentValues();
                cv.put("username",uName);
                cv.put("password", pWord);

                if(!cpWprd.equals(pWord)){
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

                else if(uName.isEmpty()||pWord.isEmpty()||cpWprd.isEmpty()){
                    Toast.makeText(SignUp.this, "Missing Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    signUp(SignUp.this,cv);
                }

            }
        });


    }

    public static void signUp(final Context c , ContentValues cv){
        new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1355485/adduser.php", cv) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("1")){
                    Toast.makeText(c, "Your account has been successfully created", Toast.LENGTH_SHORT).show();

                }

                else{
                    Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
