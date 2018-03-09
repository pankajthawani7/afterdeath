package com.example.pankajthawani.afterdeathcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnHelp extends AppCompatActivity {
    Button getHelp;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_help);
        t=findViewById(R.id.help);
        getHelp=findViewById(R.id.back);
        //Code to go back from help to Register activity..
        getHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(OnHelp.this,Register.class);
                startActivity(i);
            }
        });
    }

}
