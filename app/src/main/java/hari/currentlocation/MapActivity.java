package hari.currentlocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static void start(Context context, double latitude, double longitude) {
        Intent starter = new Intent(context, MapActivity.class);
        starter.putExtra(Constant.extra.LATITUDE, latitude);
        starter.putExtra(Constant.extra.LONGITUDE, longitude);
        context.startActivity(starter);
    }

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng loc = new LatLng(getIntent().getDoubleExtra(Constant.extra.LATITUDE, 0), getIntent().getDoubleExtra(Constant.extra.LONGITUDE, 0));
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(loc)).setVisible(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
        }
    }
}
