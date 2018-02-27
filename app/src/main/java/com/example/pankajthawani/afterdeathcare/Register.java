package com.example.pankajthawani.afterdeathcare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import java.lang.String;
import java.util.Random;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    TextView register;
    EditText e1,e2;
    Button button;
    static String name,mob;
    static Random random=new Random();
    static int num=random.nextInt(100000);
    static String number=String.valueOf(num);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register);
        e1=findViewById(R.id.name);
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
                    smsManager.sendTextMessage(e2.getText().toString(),null,"Your OTP is "+number,null,null);
                    Toast.makeText(Register.this, "OTP send", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(Register.this, ""+e, Toast.LENGTH_SHORT).show();
                }
                name=e1.getText().toString();
                mob=e2.getText().toString();
            }
        });
    }
}
