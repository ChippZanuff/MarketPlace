package com.example.deepdev_03.muvito;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.crashlytics.android.Crashlytics;
import com.example.deepdev_03.muvito.Fragments.Favorites.FavoritesTab;
import com.example.deepdev_03.muvito.Fragments.HomePage.HomePageTab;
import com.example.deepdev_03.muvito.Fragments.UserProfile.UserProfileTab;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private TabLayout bottomTabs;
    private LinearLayout tablayoutHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        this.logUser();
        this.findViews();
        this.initBottomTabs();
    }

    private void findViews()
    {
        this.bottomTabs = (TabLayout) findViewById(R.id.navigation_bottom_bar);
        this.frameLayout = (FrameLayout) findViewById(R.id.main_activity_frame_layout);
        this.tablayoutHolder = (LinearLayout) findViewById(R.id.main_tablayout_holder);
    }

    public void onClickCategories(View view){
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    public void onClickFilter (View view) {
        Intent i = new Intent(this, FilterActivity.class);
        startActivity(i);
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

    private void initBottomTabs()
    {
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_home_selected));
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_star_black_24dp));
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_add_product));
        this.bottomTabs.addTab(this.bottomTabs.newTab().setIcon(R.drawable.ic_profile_unselected));
        this.bottomTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if (tab.getPosition() == 2)
                {
                    Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                    System.out.println("trying to start add item activity");
                    startActivity(intent);
                    return;
                }

                if(tab.getPosition() == 0)
                {
                    tab.setIcon(R.drawable.ic_home_selected);
                }
                else if(tab.getPosition() == 1)
                {
                    tab.setIcon(R.drawable.ic_star_selected);
                }
                else if(tab.getPosition() == 3)
                {
                    tab.setIcon(R.drawable.ic_profile_selected);
                }

                Fragment f = tabFragment(tab.getPosition());

                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.main_activity_frame_layout, f).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
                if(tab.getPosition() == 0)
                {
                    tab.setIcon(R.drawable.ic_home_black_24dp);
                }
                else if(tab.getPosition() == 1)
                {
                    tab.setIcon(R.drawable.ic_star_black_24dp);
                }
                else if(tab.getPosition() == 3)
                {
                    tab.setIcon(R.drawable.ic_profile_unselected);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        Fragment f = tabFragment(0);

        getSupportFragmentManager()
                .beginTransaction().replace(R.id.main_activity_frame_layout, f).commit();
    }

    private Fragment tabFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new HomePageTab();
            case 1:
                return new FavoritesTab();
            case 3:
                return new UserProfileTab();
            default:
                return null;
        }
    }
}