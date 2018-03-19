package com.example.pankajthawani.afterdeathcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pankajthawani.afterdeathcare.R;

public class SignUpPage extends AppCompatActivity {
    TextView t;
    EditText e1;
    Button b1;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        t=findViewById(R.id.login);
        e1=findViewById(R.id.otp);
        b1=findViewById(R.id.lgnbtn);
        sharedPreferences=getSharedPreferences("sp", Context.MODE_PRIVATE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              try {
                  //Code to check weather the entered OTP is correct or not..
                  if (Register.number.toString().equals(e1.getText().toString()))
                  {
                      Intent intent=new Intent(SignUpPage.this,Dashboard.class);
                      startActivity(intent);
                  }
                  else
                  {
                      Toast.makeText(SignUpPage.this, "enter valid OTP", Toast.LENGTH_SHORT).show();
                  }
              }

              catch (Exception e)
              {
                  e.printStackTrace();
              }
                String otp;
                otp=Register.number;
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("otp",Register.number);
                editor.commit();
            }
        });
}
}
