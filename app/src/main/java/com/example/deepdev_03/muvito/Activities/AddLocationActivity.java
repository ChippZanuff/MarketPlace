package com.example.deepdev_03.muvito.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.deepdev_03.muvito.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddLocationActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.user_profile_settings_add_map_location);
        mapFragment.getMapAsync(this);
    }

    public void onClickBackLocation (View view){
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        this.map = googleMap;
        LatLng tokyoUniversity = new LatLng(35.7126775, 139.76198899999997);
        this.map.addMarker(new MarkerOptions().position(tokyoUniversity));
        this.map.moveCamera(CameraUpdateFactory.newLatLng(tokyoUniversity));
    }

    // TODO: notification summary size; profile edit toolbar text size;
    // TODO: black list date kudato propala;
    // TODO: black list dialog img size;
    //TODO: offers activity textview похожие объявления; hide 3 tochki share with
}
