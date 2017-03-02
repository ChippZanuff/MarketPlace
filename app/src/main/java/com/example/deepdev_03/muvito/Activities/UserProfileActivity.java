package com.example.deepdev_03.muvito.Activities;

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
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.deepdev_03.muvito.Adapters.UserProfilePagerAdapter;
import com.example.deepdev_03.muvito.Dialogs.UserProfileDetailsDialog;
import com.example.deepdev_03.muvito.R;

public class UserProfileActivity extends AppCompatActivity
{
    private ImageView avatar;
    private ImageButton details;
    private TabLayout topTabs;
    private RatingBar ratingBar;
    private TextView userName, userLocation;
    private Toolbar toolbar;
    private UserProfilePagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);

        this.findViews();
        this.initTopTabs();
        this.initToolbar();

        this.details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UserProfileDetailsDialog dialog = new UserProfileDetailsDialog();

                dialog.show(getSupportFragmentManager(), "ABC");
            }
        });
    }

    private void findViews()
    {
        this.avatar = (ImageView) findViewById(R.id.user_profile_avatar);
        this.avatar.setImageBitmap(this.getCroppedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.devka)));
        this.details = (ImageButton) findViewById(R.id.user_profile_details);
        this.topTabs = (TabLayout) findViewById(R.id.user_profile_tabs);
        this.ratingBar = (RatingBar) findViewById(R.id.user_profile_ratingBar);
        this.userLocation = (TextView) findViewById(R.id.user_profile_location);
        this.userName = (TextView) findViewById(R.id.user_profile_name);
        this.toolbar = (Toolbar) findViewById(R.id.user_profile_toolbar);
        this.viewPager = (ViewPager) findViewById(R.id.user_profile_view_pager);
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back_collapsed);
            toolbar.setNavigationOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onBackPressed();
                }
            });
        }
    }

    private void initTopTabs()
    {
        this.topTabs.addTab(this.topTabs.newTab().setText("Sell"));
        this.topTabs.addTab(this.topTabs.newTab().setText("Sold"));
        this.topTabs.addTab(this.topTabs.newTab().setText("Reviews"));
        this.adapter = new UserProfilePagerAdapter(getSupportFragmentManager(), topTabs.getTabCount());
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
