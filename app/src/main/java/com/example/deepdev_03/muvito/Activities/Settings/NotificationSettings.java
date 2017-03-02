package com.example.deepdev_03.muvito.Activities.Settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.deepdev_03.muvito.R;

public class NotificationSettings extends AppCompatActivity
{
    private RelativeLayout messagesHolder, favoritesHolder, myAdvertisementsHolder;
    private SwitchCompat messages, favorites, advertisemenst;
    private TextView reset;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        this.findViews();
        this.initToolbar();
    }

    private void findViews()
    {
        this.messagesHolder = (RelativeLayout) findViewById(R.id.user_profile_settings_messages);
        this.favoritesHolder = (RelativeLayout) findViewById(R.id.user_profile_settings_favorite);
        this.myAdvertisementsHolder = (RelativeLayout) findViewById(R.id.user_profile_settings_advertisements);
        this.messages = (SwitchCompat) findViewById(R.id.messages_switch);
        this.favorites = (SwitchCompat) findViewById(R.id.favorite_switch);
        this.advertisemenst = (SwitchCompat) findViewById(R.id.advertisements_switch);
        this.toolbar = (Toolbar) findViewById(R.id.notifications_toolbar);
    }

    private void initToolbar()
    {
        this.toolbar.setTitle("Уведомления");
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
