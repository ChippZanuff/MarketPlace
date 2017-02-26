package com.example.deepdev_03.muvito.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.deepdev_03.muvito.Fragments.UserProfile.Tabs.Reviews;
import com.example.deepdev_03.muvito.Fragments.UserProfile.Tabs.Sell;
import com.example.deepdev_03.muvito.Fragments.UserProfile.Tabs.Sold;

public class UserProfilePagerAdapter extends FragmentStatePagerAdapter
{
    private int numOfTabs;

    public UserProfilePagerAdapter(FragmentManager fragmentManager, int numOfTabs)
    {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new Sell();
            case 1:
                return new Sold();
            case 2:
                return new Reviews();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return this.numOfTabs;
    }
}
