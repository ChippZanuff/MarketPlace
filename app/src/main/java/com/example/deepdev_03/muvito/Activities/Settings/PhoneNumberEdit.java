package com.example.deepdev_03.muvito.Activities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.deepdev_03.muvito.R;

public class PhoneNumberEdit extends AppCompatActivity
{
    private Button changeNumber;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_phone_number_edit);
        this.findViews();
        this.initToolbar();

        this.changeNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), NumberInput.class);
                startActivity(intent);
            }
        });
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }

    private void findViews()
    {
        this.toolbar = (Toolbar) findViewById(R.id.profile_change_number_toolbar);
        this.changeNumber = (Button) findViewById(R.id.user_profile_change_number_button);
    }
}
