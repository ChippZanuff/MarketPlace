package com.example.deepdev_03.muvito;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.deepdev_03.muvito.Adapters.RecyclerView.GridSpacingItemDecoration;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItemsAdapter;
import com.example.deepdev_03.muvito.Model.OffersItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class OffersActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private final String OFFERS_KEY = "offerItem";
    private final String EXTRA_IMAGE = "com.example.deepdev_03.muvito.TopCollapsingImage";
    private AppBarLayout appBar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    //private ImageView collapsingImage;
    private NestedScrollView scrollView;
    private OffersItem item;
    private GoogleMap map;
    private ImageView sellerAvatar, shareWith, shareVk;
    private Button call, sendEmail;
    private ViewPager imagePager;
    private ArrayList<Integer> images = new ArrayList<>();
    private RecyclerView recyclerView;
    private TabLayout dotPager;

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
        this.setImages();
        this.imagePager.setAdapter(new ImageSliderAdapter(getApplicationContext(), this.setSliderImages()));
        this.dotPager.setupWithViewPager(this.imagePager);
        this.initRecyclerView();

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_offer), this.EXTRA_IMAGE);
        supportPostponeEnterTransition();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        this.map = googleMap;
        LatLng tokyoUniversity = new LatLng(35.7126775, 139.76198899999997);
        this.map.addMarker(new MarkerOptions().position(tokyoUniversity));
        this.map.moveCamera(CameraUpdateFactory.newLatLng(tokyoUniversity));

    }

    private void findViews()
    {
        this.appBar = (AppBarLayout) findViewById(R.id.app_bar_offer);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_offer);
        this.collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_offer);
        this.scrollView = (NestedScrollView) findViewById(R.id.scroll_container_offer);
        this.shareVk = (ImageView) findViewById(R.id.vk_share);
        this.shareWith = (ImageView) findViewById(R.id.share_another_way);
        this.sellerAvatar = (ImageView) findViewById(R.id.sellers_avatar);
        this.call = (Button) findViewById(R.id.call_action);
        this.sendEmail = (Button) findViewById(R.id.send_email);
        this.imagePager = (ViewPager) findViewById(R.id.offer_image_pager);
        this.dotPager = (TabLayout) findViewById(R.id.offer_dot_pager);
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
    }

    public void OnClick(View view)
    {
        if(view.getId() == R.id.call_action)
        {

        }
        if(view.getId() == R.id.send_email)
        {

        }
        if(view.getId() == R.id.complain)
        {

        }
    }

    private void setImages()
    {
        this.sellerAvatar.setImageBitmap(this.getCroppedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.devka)));
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    private ArrayList<Integer> setSliderImages()
    {
        int[] images = {R.drawable.devka, R.drawable.empty_img, R.drawable.devka, R.drawable.empty_img};
        for(int i = 0; i < images.length; i++)
        {
            this.images.add(images[i]);
        }

        return this.images;
    }

    private void initRecyclerView()
    {
        OfferItemsAdapter adapter = new OfferItemsAdapter(this.getItems());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        View includedRecycler = findViewById(R.id.recycler_view);
        this.recyclerView = (RecyclerView) includedRecycler.findViewById(R.id.recycler_view);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 2, false));
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setNestedScrollingEnabled(false);
    }

    private ArrayList<OffersItem> getItems()
    {
        Bitmap image;
        ArrayList<OffersItem> items = new ArrayList<>();

        for(int i = 0; i <= 10; i++)
        {
            image = BitmapFactory.decodeResource(getResources(), R.drawable.empty_img);
            items.add(new OffersItem(image, i + " km", i + " rubley", i + " description", i + " name", i + "date", i));
        }

        return items;
    }
}
