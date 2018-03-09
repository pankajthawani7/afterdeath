package com.example.pankajthawani.afterdeathcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    SharedPreferences d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        d=getSharedPreferences("sp",Context.MODE_PRIVATE);
        final String otp= d.getString("otp",null);


        Thread thread=new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);
                }catch (Exception e)
                {
                }
                finally {
                    if (otp!=null)
                    {
                        Intent intent=new Intent(Splash.this,Dashboard.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent=new Intent(Splash.this,Register.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }
        };
        thread.start();
    }
}
