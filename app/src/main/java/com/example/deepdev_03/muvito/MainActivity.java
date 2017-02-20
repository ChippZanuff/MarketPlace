package com.example.deepdev_03.muvito;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.crashlytics.android.Crashlytics;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.GridSpacingItemDecoration;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.HidingScrollListener;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItemsAdapter;
import com.example.deepdev_03.muvito.Model.OffersItem;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String OFFERS_KEY = "offerItem";
    EditText editText;
    ArrayAdapter<String> adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TabLayout bottomTabs;
    private OfferItemsAdapter itemsAdapter;
    private Toolbar myToolbar;
    private ArrayList<OffersItem> items = new ArrayList<>();
    private LinearLayout toolbarHolder, tablayoutHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        this.logUser();
        //this.forceCrash(this.toolbarHolder);

        this.myToolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(myToolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            editText = (EditText) findViewById(R.id.editTextMainActivity);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        editText.setHint("Поиск");
                    }
                }
            });
        }

        this.items = this.getItems();
        this.initBottomTabs();
        this.initRecyclerView();
        this.findViews();
    }

    private void findViews()
    {
        this.tablayoutHolder = (LinearLayout) findViewById(R.id.main_tablayout_holder);
        this.toolbarHolder = (LinearLayout) findViewById(R.id.main_toolbar_holder);
    }

    public void onClickCategories(View view){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }


    public void onClickFilter (View view) {
        Intent i = new Intent(this, FilterActivity.class);
        startActivity(i);
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

    private void initRecyclerView()
    {
        this.layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        this.itemsAdapter = new OfferItemsAdapter(this.items);
        View includedRecycler = findViewById(R.id.recycler_view);
        this.recyclerView = (RecyclerView) includedRecycler.findViewById(R.id.recycler_view);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 2, false));
        this.recyclerView.setAdapter(this.itemsAdapter);
        this.recyclerView.addOnScrollListener(new HidingScrollListener()
        {
            @Override
            public void onHide()
            {
                hideViews();
            }

            @Override
            public void onShow()
            {
                showViews();
            }
        });

        ItemClickSupport.addTo(this.recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener()
        {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v)
            {
                Intent intent = new Intent(getApplicationContext(), OffersActivity.class);
                intent.putExtra(OFFERS_KEY, items.get(position));
                System.out.println("trying to start offers activity");
                startActivity(intent);
            }
        });
    }

    private void initBottomTabs()
    {
        this.bottomTabs = (TabLayout) findViewById(R.id.navigation_bottom_bar);
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_home_black_24dp));
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_star_black_24dp));
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_add_circle_black_24dp));
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_perm_contact_calendar_black_24dp));
    }

    private void hideViews() {
        this.toolbarHolder.animate().translationY(-this.toolbarHolder.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.tablayoutHolder.getLayoutParams();
        int tabsBottomMargin = lp.bottomMargin;
        this.tablayoutHolder.animate().translationY(this.tablayoutHolder.getHeight()+tabsBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        this.toolbarHolder.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        this.tablayoutHolder.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }


}