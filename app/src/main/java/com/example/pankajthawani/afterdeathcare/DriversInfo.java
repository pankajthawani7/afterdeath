package com.example.pankajthawani.afterdeathcare;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriversInfo extends AppCompatActivity {
    TextView info;
    Button calld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_info);
        info = findViewById(R.id.drinfo);
        calld = findViewById(R.id.calld);

        StringBuilder sb = new StringBuilder();
        sb.append("Name-" + "Prashu Bansal" + "\n\n");
        sb.append("Van No-" + "RJ08CJ4287" + "\n\n");
        sb.append("Contact No-" + "9261455600" + "\n\n");
        sb.append("Address-" + "Mahavir Nagar" + "\n\n");
        info.setText(sb.toString());

        calld.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:9261455600"));
                startActivity(intent);
            }
        });


    }
}
