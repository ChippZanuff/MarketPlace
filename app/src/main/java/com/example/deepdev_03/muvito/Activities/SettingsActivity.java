package com.example.deepdev_03.muvito.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.deepdev_03.muvito.Fragments.UserProfile.Settings.UserProfileMainSettings;
import com.example.deepdev_03.muvito.R;

public class SettingsActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_settings_toolbar);
        this.initToolbar();

        getSupportFragmentManager().beginTransaction().replace(R.id.user_profile_settings_fragment_container,
                new UserProfileMainSettings()).commit();
    }

    private void initToolbar()
    {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("Settings");
        this.toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back_collapsed);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }
}
