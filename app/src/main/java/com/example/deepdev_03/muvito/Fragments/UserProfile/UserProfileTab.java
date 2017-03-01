package com.example.deepdev_03.muvito.Fragments.UserProfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.deepdev_03.muvito.Activities.SettingsActivity;
import com.example.deepdev_03.muvito.Adapters.UserProfilePagerAdapter;
import com.example.deepdev_03.muvito.MainActivity;
import com.example.deepdev_03.muvito.R;

public class UserProfileTab extends Fragment implements View.OnClickListener
{
    private ImageView avatar;
    private ImageButton share, options;
    private TabLayout topTabs;
    private RatingBar ratingBar;
    private TextView userName, userLocation;
    private Toolbar toolbar;
    private UserProfilePagerAdapter adapter;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.user_profile, container, false);
        this.findViews(rootView);
        this.initTopTabs();
        this.initToolbarButtons();
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        if(((MainActivity)getActivity()).getSupportActionBar() != null)
        {
            ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.user_profile_options)
        {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.user_profile_details)
        {
            System.out.println("yo");
        }
    }

    private void findViews(View root)
    {
        this.avatar = (ImageView) root.findViewById(R.id.user_profile_avatar);
        this.avatar.setImageBitmap(this.getCroppedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.devka)));
        this.share = (ImageButton) root.findViewById(R.id.user_profile_details);
        this.options = (ImageButton) root.findViewById(R.id.user_profile_options);
        this.topTabs = (TabLayout) root.findViewById(R.id.user_profile_tabs);
        this.ratingBar = (RatingBar) root.findViewById(R.id.user_profile_ratingBar);
        this.userLocation = (TextView) root.findViewById(R.id.user_profile_location);
        this.userName = (TextView) root.findViewById(R.id.user_profile_name);
        this.toolbar = (Toolbar) root.findViewById(R.id.user_profile_toolbar);
        this.viewPager = (ViewPager) root.findViewById(R.id.user_profile_view_pager);
    }

    private void initTopTabs()
    {
        this.topTabs.addTab(this.topTabs.newTab().setText("Sell"));
        this.topTabs.addTab(this.topTabs.newTab().setText("Sold"));
        this.topTabs.addTab(this.topTabs.newTab().setText("Reviews"));
        this.adapter = new UserProfilePagerAdapter(getFragmentManager(), topTabs.getTabCount());
        this.viewPager.setAdapter(adapter);
        this.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(topTabs));
        this.topTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    private void initToolbarButtons()
    {
        this.share.setOnClickListener(this);
        this.options.setOnClickListener(this);
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
