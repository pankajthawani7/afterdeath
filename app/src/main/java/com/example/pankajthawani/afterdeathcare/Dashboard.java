package com.example.pankajthawani.afterdeathcare;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    TextView getLocation;
    Double lag,log;
    static String fulladdress;
    String address,area,city,country,postalcode;
    Button find;
    LocationManager locationManager;
    LocationListener locationListener;
    List<Address>addresses;
    Geocoder geocoder;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getLocation=findViewById(R.id.comson);
        find=findViewById(R.id.fnd);

         locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lag=location.getLatitude();
                log=location.getLongitude();
               // Toast.makeText(Dashboard.this, ""+fulladdress, Toast.LENGTH_LONG).show();
                Toast.makeText(Dashboard.this, ""+lag+"\n"+log, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET,
            },10);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
    }

    public void onRequestPermissionsResult(int requestcode,String[] permission,int[] grantResult) {
        switch (requestcode){
            case 10:
                if (grantResult.length>0 && grantResult[0]==PackageManager.PERMISSION_GRANTED)
                {
                    find.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onClick(View view) {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
                        }
                    });
                }
        }
        geocoder=new Geocoder(this, Locale.getDefault());
        try {
            addresses=geocoder.getFromLocation(lag,log,1);
            address=addresses.get(0).getAddressLine(0);
            area=addresses.get(0).getLocality();
            city=addresses.get(0).getAdminArea();
            country=addresses.get(0).getCountryName();
            postalcode=addresses.get(0).getCountryCode();
             fulladdress=address+", "+area+", "+city+", "+country+", "+postalcode;
            Toast.makeText(this, ""+Dashboard.fulladdress, Toast.LENGTH_LONG).show();
            getLocation.setText(Dashboard.fulladdress);
        } catch (Exception e) {
            e.printStackTrace();
        }

            find.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        //Code to Send Location from user's number to Driver's number
                    Toast.makeText(Dashboard.this, "Code Abhi baki h mere dost", Toast.LENGTH_LONG).show();

                }
            });
    }
}

