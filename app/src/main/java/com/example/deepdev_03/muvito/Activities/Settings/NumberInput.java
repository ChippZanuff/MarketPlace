package com.example.deepdev_03.muvito.Activities.Settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deepdev_03.muvito.R;

public class NumberInput extends AppCompatActivity
{
    private EditText phoneInput;
    private Button validateButton;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_change_phone_number);

        this.findViews();
        this.initToolbar();
    }

    private void initToolbar()
    {
        this.toolbar = (Toolbar) findViewById(R.id.user_profile_settings_number_edit_change_number_toolbar);
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

    private void findViews()
    {
        this.phoneInput = (EditText) findViewById(R.id.user_profile_settings_number_edit_input);
        this.validateButton = (Button) findViewById(R.id.user_profile_settings_number_edit_button_validate);
    }
}
