package com.example.pankajthawani.afterdeathcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Register extends AppCompatActivity {
    TextView register,helpAct;
   static EditText getName,getMobile;
    Button registerBtn;
    static String name,mob;
    static Random random=new Random();
    static int num=random.nextInt(100000)+1;
    static String number=String.valueOf(num);
    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register);
        getName=findViewById(R.id.name);
        helpAct=findViewById(R.id.help);
        getMobile=findViewById(R.id.phonenum);
        registerBtn=findViewById(R.id.regbtn);
        sf=getSharedPreferences("sp", Context.MODE_PRIVATE);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //Code to check weather the inputed number is of 10 digit or either name is empty.
            public void onClick(View view) {
                if (getMobile.length()!=10 && getName.length()==0)
                {
                    Toast.makeText(Register.this, "Enter Valid Mobile No.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent i=new Intent(Register.this,SignUpPage.class);
                    startActivity(i);
                }
                //Code to send OTP
                try {
                    SmsManager smsManager= SmsManager.getDefault();
                    smsManager.sendTextMessage(getMobile.getText().toString(),null,"Hello "+getName.getText().toString()+"\n"+"Your OTP is "+number,null,null);
                    Toast.makeText(Register.this, "OTP send", Toast.LENGTH_SHORT).show();
                    send_data();

                }
                catch (Exception e)
                {
                    Toast.makeText(Register.this, "Exception is "+e,Toast.LENGTH_SHORT).show();
                }
                SharedPreferences.Editor editor=sf.edit();
                name=getName.getText().toString();
                mob=getMobile.getText().toString();
                editor.putString("name",name);
                editor.putString("mobile",mob);
                editor.commit();
            }

        });
    }
    //Code to enter Data into Server using Volley..
    public void send_data() {
        StringRequest st=new StringRequest(Request.Method.POST, "https://codewebtk.000webhostapp.com/webservices/afterdeathcare.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Register.this, ""+response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Register.this, "Error= "+error, Toast.LENGTH_SHORT).show();
            }

        })

        {
                //Code to put data into database..
                protected Map<String,String> getParams(){
                Map<String,String> mp;
                mp=new HashMap<String,String>();
                mp.put("name",getName.getText().toString());
                mp.put("contact",getMobile.getText().toString());
                return  mp;
            }


        };
        //Add data into queue..
        RequestQueue q= Volley.newRequestQueue(Register.this);
        q.add(st);

    }
    //Code to go to help Activity using Intent
    public void on_help(View view)
    {
        Intent i=new Intent(Register.this,OnHelp.class);
        startActivity(i);
    }
}
