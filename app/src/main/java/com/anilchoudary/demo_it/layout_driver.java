package com.anilchoudary.demo_it;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class layout_driver extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_driver);
        String[] routes = {"Route-1","Route-2","Route-3 ","Route-4","Route-5","Route-6","Route-7","Route-8","Route-9","Route-10","Route-11","Route-12","Route-13","Route-14","Route-15","Route-16","Route-17"};

        ListAdapter buckyadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,routes);
        ListView buckysListView = (ListView)findViewById(R.id.buckysListView);
        buckysListView.setAdapter(buckyadapter);


        buckysListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent driver = new Intent(getApplicationContext(),layout_driver_primary.class);
                startActivity(driver);
            }
        });









    }


}
