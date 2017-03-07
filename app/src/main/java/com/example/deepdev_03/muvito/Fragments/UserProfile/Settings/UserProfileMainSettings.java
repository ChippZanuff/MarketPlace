package com.example.deepdev_03.muvito.Fragments.UserProfile.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.deepdev_03.muvito.Activities.Settings.BlackList;
import com.example.deepdev_03.muvito.Activities.Settings.NotificationSettings;
import com.example.deepdev_03.muvito.Activities.Settings.PhoneNumberEdit;
import com.example.deepdev_03.muvito.Activities.Settings.ProfileEdit;
import com.example.deepdev_03.muvito.Activities.AddLocationActivity;
import com.example.deepdev_03.muvito.R;

public class UserProfileMainSettings extends Fragment implements View.OnClickListener
{
    private ImageView locationImg, phoneImg, profileImg;
    private TextView profileSummary, profileName, phoneSummary,
            phoneNumber, locationSummary, locationAddress,
            blackList;
    private RelativeLayout location, phone, profile, notificationSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.user_profile_settings_main_fragment, container, false);
        this.findViews(rootView);
        this.setClickListener(rootView);
        this.initViews();

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.user_profile_settings_profile)
        {
            Intent intent = new Intent(getContext(), ProfileEdit.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.user_profile_settings_phone_number)
        {
            Intent intent = new Intent(getContext(), PhoneNumberEdit.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.user_profile_settings_location)
        {
            Intent intent = new Intent(getContext(), AddLocationActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.user_profile_settings_black_list)
        {
            Intent intent = new Intent(getContext(), BlackList.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.user_profile_notification_settings)
        {
            Intent intent = new Intent(getContext(), NotificationSettings.class);
            startActivity(intent);
        }
    }

    private void setClickListener(View root)
    {
        this.location = (RelativeLayout) root.findViewById(R.id.user_profile_settings_location);
        this.location.setOnClickListener(this);
        this.phone = (RelativeLayout) root.findViewById(R.id.user_profile_settings_phone_number);
        this.phone.setOnClickListener(this);
        this.profile = (RelativeLayout) root.findViewById(R.id.user_profile_settings_profile);
        this.profile.setOnClickListener(this);
        this.blackList = (TextView) root.findViewById(R.id.user_profile_settings_black_list);
        this.blackList.setOnClickListener(this);
        this.notificationSettings = (RelativeLayout) root.findViewById(R.id.user_profile_notification_settings);
        this.notificationSettings.setOnClickListener(this);
    }

    private void findViews(View root)
    {
        this.locationImg = (ImageView) root.findViewById(R.id.location_icon);
        this.locationSummary = (TextView) root.findViewById(R.id.location_summary);
        this.locationAddress = (TextView) root.findViewById(R.id.location_title);
        this.profileImg = (ImageView) root.findViewById(R.id.profile_icon);
        this.profileSummary = (TextView) root.findViewById(R.id.profile_summary);
        this.profileName = (TextView) root.findViewById(R.id.profile_title);
        this.phoneImg = (ImageView) root.findViewById(R.id.phone_number_icon);
        this.phoneSummary = (TextView) root.findViewById(R.id.phone_number_summary);
        this.phoneNumber = (TextView) root.findViewById(R.id.phone_number_title);
    }

    private void initViews()
    {
        this.locationImg.setBackgroundResource(R.drawable.ic_location);
        this.profileImg.setBackgroundResource(R.drawable.ic_settings_profile);
        this.phoneImg.setBackgroundResource(R.drawable.ic_phone);
    }
}
