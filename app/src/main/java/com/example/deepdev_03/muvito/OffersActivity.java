package com.example.deepdev_03.muvito;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.widget.ImageView;

import com.example.deepdev_03.muvito.Model.OffersItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OffersActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private final String OFFERS_KEY = "offerItem";
    private final String EXTRA_IMAGE = "com.example.deepdev_03.muvito.TopCollapsingImage";
    private AppBarLayout appBar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView collapsingImage;
    private NestedScrollView scrollView;
    private OffersItem item;
    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.initActivityTransitions();
        setContentView(R.layout.activity_offer);

        Intent intent = getIntent();
        item = intent.getParcelableExtra(this.OFFERS_KEY);
        this.findViews();
        this.initToolbar();
        this.setCollapsingToolbarLayout();

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_offer), this.EXTRA_IMAGE);
        supportPostponeEnterTransition();
        this.mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        LatLng tokyoUniversity = new LatLng(35.7126775, 139.76198899999997);
        googleMap.addMarker(new MarkerOptions().position(tokyoUniversity));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(tokyoUniversity));
    }

    private void findViews()
    {
        this.appBar = (AppBarLayout) findViewById(R.id.app_bar_offer);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_offer);
        this.collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_offer);
        this.collapsingImage = (ImageView) findViewById(R.id.collapse_image_offer);
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.devka);
        this.collapsingImage.setImageBitmap(image);
        this.scrollView = (NestedScrollView) findViewById(R.id.scroll_container_offer);
        this.mapView = (MapView) findViewById(R.id.google_map);
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void setCollapsingToolbarLayout()
    {
        collapsingToolbarLayout.setTitle(item.getName());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private void initToolbar()
    {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
