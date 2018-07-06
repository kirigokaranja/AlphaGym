package com.kirigokaranja.alphagym;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeGym extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mgoogleApiClient;
    String URL_DATA = "http://alphagymapplication.herokuapp.com/api/gym";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_gym);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (mMap != null) {

            mgoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            mgoogleApiClient.connect();

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View view = getLayoutInflater().inflate(R.layout.mapsinfowindow, null);

                    TextView name = (TextView) view.findViewById(R.id.locationName);
                    TextView open = (TextView) view.findViewById(R.id.locationopen);
                    TextView close = (TextView) view.findViewById(R.id.locationclose);

                    if(marker.getSnippet() != null) {

                        String[] times = marker.getSnippet().split(",");
                        LatLng latLng = marker.getPosition();
                        name.setText(marker.getTitle());
                        open.setText("Opening Time: " + times[0]);
                        close.setText("Closing Time: " + times[1]);
                    }else {
                        name.setText("Current Location");
                    }
                    return view;
                }
            });
        } else
        {
            Toast.makeText(this, "Turn on your location", Toast.LENGTH_LONG).show();
        }
        maps();
    }

    public void goToLocation(double lat, double lng) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(latLng);
        mMap.moveCamera(update);
    }

    public void goToLocationzoom(double lat, double lng, float zoom) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }


    LocationRequest mrequest;
    Marker marker;

    @Override
    public void onConnected(Bundle bundle) {

        mrequest = LocationRequest.create();
        mrequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mrequest.setInterval(3000);

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
        LocationServices.FusedLocationApi.requestLocationUpdates(mgoogleApiClient, mrequest, (com.google.android.gms.location.LocationListener) this);

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mgoogleApiClient);
        if (mLastLocation != null)
        {
            double lat = mLastLocation.getLatitude();
            double lng = mLastLocation.getLongitude();

            LatLng latLng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(latLng).title("You are Here"));
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null){
            Toast.makeText(this, "Cannot get current location", Toast.LENGTH_LONG).show();
        }else {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update =   CameraUpdateFactory.newLatLngZoom(latLng, 10);
            mMap.animateCamera(update);
            setMarker(latLng);

        }
    }

    private void setMarker(LatLng latLng) {
        if (marker != null){
            marker.remove();
        }

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng);
        marker = mMap.addMarker(markerOptions);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void maps()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {


                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject map = array.getJSONObject(i);
                                MarkerOptions options = new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gicon))
                                        .title(map.getString("gym_name"))
                                        .position(new LatLng(map.getDouble("latitude"), map.getDouble("longitude")))
                                        .snippet(map.getString("opening_time") + "," + map.getString("closing_time"));
                                mMap.addMarker(options);
                            }
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
