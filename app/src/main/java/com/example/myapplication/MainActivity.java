package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    Location oldLocation = null;

    TextView txtVw_info;
    boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtVw_status = findViewById(R.id.txtVw_status);
        txtVw_info = findViewById(R.id.txtVw_info);

        Button btn_start = findViewById(R.id.btn_start);
        Button btn_map = findViewById(R.id.btn_map);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new MyLocationListener();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recording){
                    recording = false;
                    btn_start.setText("Start");
                    txtVw_status.setText("Recording Stop");
                }
                else {
                    recording = true;
                    btn_start.setText("Stop");
                    txtVw_status.setText("Start recording");
                }
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (recording) {
                Log.d("LOCATION: Location", location.getLatitude() + " " + location.getLongitude());
                Log.d("LOCATION: Speed", location.getSpeed() + "");

                Data data = new Data(location, oldLocation, MainActivity.this);

                txtVw_info.setText("LOCATION: "+data.getLatitude() + " " + data.getLongitude()
                        + "\nSpeed: " + data.getSpeed() + "\nTime: " + data.getTimestamp() + "\nTime: "
                        + "\nAcceleration: " + data.getAcceleration());

                oldLocation = location;


            }
            else{
                txtVw_info.setText("");
            }
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
}