package com.example.deepdev_03.muvito.Activities.Settings;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deepdev_03.muvito.Adapters.BlackListAdapter;
import com.example.deepdev_03.muvito.Dialogs.BlackListDialog;
import com.example.deepdev_03.muvito.Model.UserData;
import com.example.deepdev_03.muvito.R;

import java.util.ArrayList;

public class BlackList extends AppCompatActivity
{
    private Toolbar toolbar;
    private ListView listView;
    private ArrayList<UserData> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_settings_black_list);

        this.findViews();
        this.initToolbar();
        this.initList();
    }

    private void findViews()
    {
        this.toolbar = (Toolbar) findViewById(R.id.black_list_toolbar);
        this.listView = (ListView) findViewById(R.id.user_profile_black_lists_list_view);
    }

    private void initToolbar()
    {
        this.toolbar.setTitle("Черный список");
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

    private ArrayList<UserData> getItems()
    {
        Bitmap image;
        ArrayList<UserData> list = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            image = BitmapFactory.decodeResource(getResources(), R.drawable.devka);
            list.add(new UserData(i + "name", i + "location", i + "date", image, 3));
        }

        return list;
    }

    private void initList()
    {
        this.list = this.getItems();
        BlackListAdapter adapter = new BlackListAdapter(getApplicationContext(), this.list);
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                BlackListDialog dialog = new BlackListDialog();

                dialog.show(getSupportFragmentManager(), "ABC");
            }
        });
    }
}
