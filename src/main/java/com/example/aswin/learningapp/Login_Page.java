package com.example.aswin.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login_Page extends FragmentActivity  {
    Button next_button;
    Button sign_up;
    EditText _password;
    EditText mail_id;
    String pass;
    String mail;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        next_button=(Button) findViewById(R.id.button2);
        sign_up=(Button) findViewById(R.id.button3);
_password=(EditText) findViewById(R.id.editText2);
        mail_id=(EditText) findViewById(R.id.editText);


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pass=_password.getText().toString();
                mail=mail_id.getText().toString();

                if(pass.length()>0&&mail.indexOf("@gmail.com")!=-1){
                    Intent intent=new Intent(Login_Page.this,Current_Location.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Enter Valid Information",Toast.LENGTH_SHORT).show();
                }
                }

        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("jhj",mail+"  "+pass);

                Intent intent=new Intent(Login_Page.this,Details.class);
                startActivity(intent);
            }
        });
 }

}