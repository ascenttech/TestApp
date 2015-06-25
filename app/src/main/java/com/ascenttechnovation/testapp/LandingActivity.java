package com.ascenttechnovation.testapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ADMIN on 24-06-2015.
 */
public class LandingActivity extends Activity {

    GoogleMap myMap;
    double longitude,latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        createMapView();
        addMarker(19.097,72.913);
        Button bt1= (Button) findViewById(R.id.button);
        Button bt2= (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                Criteria criteria = new Criteria();
//                String provider = locationManager.getBestProvider(criteria, true);
//                Location mylocation = locationManager.getLastKnownLocation(provider);
//                longitude = mylocation.getLongitude();
//                latitude = mylocation.getLatitude();
//                addMarker(latitude,longitude);

//
//                FragmentManager myFragmentManager = getFragmentManager();
//                MapFragment myMapFragment = (MapFragment) myFragmentManager.findFragmentById(R.id.mapView);
//                myMap = myMapFragment.getMap();
//                myMap.getUiSettings().setZoomControlsEnabled(true);

//
//                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(myMap);
//                myMap = supportMapFragment.getMap();
//                myMap.setMyLocationEnabled(true);
//                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//                Criteria criteria = new Criteria();
//                String bestProvider = locationManager.getBestProvider(criteria, true);
//                Location location = locationManager.getLastKnownLocation(bestProvider);
//                if (location != null) {
//                    onLocationChange(location);
//                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude + "mode:w");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
    public void onLocationChange(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        myMap.addMarker(new MarkerOptions().position(latLng));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        myMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }


    @Override
    public void onBackPressed() {
    }
    private void createMapView(){
        try {
            if(null == myMap){
                myMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
                myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                if(null == myMap) {
                    Toast.makeText(getApplicationContext(),"Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }
    private void addMarker(double latitude,double longitude){
        if(null != myMap){
            myMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker").draggable(true));
            LatLng latLng = new LatLng(latitude, longitude);
            myMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            myMap.animateCamera(CameraUpdateFactory.zoomTo(5));
        }
    }
}
