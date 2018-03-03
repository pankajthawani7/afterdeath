package com.example.pankajthawani.afterdeathcare;

import android.content.Intent;
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
    EditText e1,e2;
    Button button;
    static String name,mob;
    static Random random=new Random();
    static int num=random.nextInt(100000)+1;
    static String number=String.valueOf(num);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register);
        e1=findViewById(R.id.name);
        helpAct=findViewById(R.id.help);
        e2=findViewById(R.id.phonenum);
        button=findViewById(R.id.regbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e2.length()!=10 && e1.length()==0)
                {
                    Toast.makeText(Register.this, "Enter Valid Mobile No.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent i=new Intent(Register.this,SignUpPage.class);
                    startActivity(i);
                }
                try {
                    SmsManager smsManager= SmsManager.getDefault();
                    smsManager.sendTextMessage(e2.getText().toString(),null,"Hello "+e1.getText().toString()+"\n"+"Your OTP is "+number,null,null);
                    Toast.makeText(Register.this, "OTP send", Toast.LENGTH_SHORT).show();
                    send_data();
                }
                catch (Exception e)
                {
                    Toast.makeText(Register.this, ""+e, Toast.LENGTH_SHORT).show();
                }
                name=e1.getText().toString();
                mob=e2.getText().toString();
            }

        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    public void send_data() {
        StringRequest st=new StringRequest(Request.Method.POST, "https://codewebtk.000webhostapp.com/webservices/afterdeathcare.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Register.this, "success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Register.this, "Error=", Toast.LENGTH_SHORT).show();
            }

        })

        {
                protected Map<String,String> getParams(){
                Map<String,String> mp;
                mp=new HashMap<String,String>();
                mp.put("name",e1.getText().toString());
                mp.put("contact",e2.getText().toString());
                return  mp;
            }


        };

        RequestQueue q= Volley.newRequestQueue(Register.this);
        q.add(st);

    }

    public void on_help(View view)
    {
        Intent i=new Intent(Register.this,OnHelp.class);
        startActivity(i);
    }
}
