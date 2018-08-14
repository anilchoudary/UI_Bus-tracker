package com.anilchoudary.demo_it;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DigitalClock;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import  java.util.Date;
import java.util.List;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class layout_driver_primary extends AppCompatActivity {

    SwipeButton ride;
    private Date d;
    LocationListener locationListener;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_driver_primary);
        DigitalClock dc = (DigitalClock)findViewById(R.id.clock);
       // SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       // TextView txt = (TextView)findViewById(R.id.textView7);
       // txt.setText(df);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        ride = (SwipeButton)findViewById(R.id.button_create);

        ride.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent i = new Intent(getApplicationContext(),layout_driver_primary.class);
                startActivity(i);
            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

               /* TextView mlat = (TextView)findViewById(R.id.textView4);
                mlat.setText(String.valueOf(latitude));
                TextView mlong = (TextView)findViewById(R.id.textView5);
                mlong.setText(String.valueOf(longitude));*/
                //mDatabase.child("Lat").setValue("latitude");
                //get the location name from latitude and longitude
               Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality()+" - ";
                    //result += addresses.get(0).getCountryName()+":";
                    result += addresses.get(0).getSubLocality()+" - ";
                    result += addresses.get(0).getPostalCode();
                    TextView res = (TextView)findViewById(R.id.textView6);
                    res.setText(result);
                    LatLng latLng = new LatLng(latitude, longitude);
                   /* mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                    mMap.setMaxZoomPreference(30);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));*/

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }
}
