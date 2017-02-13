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

import com.example.deepdev_03.muvito.Adapters.RecyclerView.GridSpacingItemDecoration;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.HidingScrollListener;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItemsAdapter;
import com.example.deepdev_03.muvito.Model.OffersItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ArrayAdapter<String> adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TabLayout bottomTabs;
    private OfferItemsAdapter itemsAdapter;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            this.myToolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
            setSupportActionBar(myToolbar);
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

        this.initBottomTabs();
        this.initRecyclerView();

    }

    public void onClickCategories(View view){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    private ArrayList<OffersItem> getItems()
    {
        Bitmap image;
        ArrayList<OffersItem> items = new ArrayList<>();

        for(int i = 0; i <= 20; i++)
        {
            image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_camera);
            items.add(new OffersItem(image, i + " km", i + " rubley", i + " description"));
        }

        return items;
    }

    private void initRecyclerView()
    {
        this.layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        this.itemsAdapter = new OfferItemsAdapter(this.getItems());
        this.recyclerView = (RecyclerView) findViewById(R.id.offers_grid);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 25, false));
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
        this.myToolbar.animate().translationY(-this.myToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.bottomTabs.getLayoutParams();
        int tabsBottomMargin = lp.bottomMargin;
        this.bottomTabs.animate().translationY(this.bottomTabs.getHeight()+tabsBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        this.myToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        this.bottomTabs.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }
}

