package com.example.deepdev_03.muvito.Fragments.HomePage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem.GridSpacingItemDecoration;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem.OfferItemsAdapterWithHeader;
import com.example.deepdev_03.muvito.MainActivity;
import com.example.deepdev_03.muvito.Model.OffersItem;
import com.example.deepdev_03.muvito.OffersActivity;
import com.example.deepdev_03.muvito.R;
import com.example.deepdev_03.muvito.Utils.HidingScrollListener;
import com.example.deepdev_03.muvito.Utils.ItemClickSupportWithHeader;

import java.util.ArrayList;

public class HomePageTab extends Fragment
{
    private final String OFFERS_KEY = "offerItem";
    private ArrayList<OffersItem> items;
    private EditText editText;
    private OfferItemsAdapterWithHeader itemsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar myToolbar;
    private LinearLayout toolbarHolder, tablayoutHolder;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.home_tab_fragment, container, false);
        this.findViews(rootView);
        this.items = getItems();
        this.initRecyclerView();
        this.initSwipeRefresh();

        ((MainActivity)getActivity()).setSupportActionBar(myToolbar);

        if(((MainActivity)getActivity()).getSupportActionBar() != null)
        {
            ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus)
                    {
                        editText.setHint("Поиск");
                    }
                }
            });
        }
        return rootView;
    }

    private void findViews(View root)
    {
        this.recyclerView = (RecyclerView) root.findViewById(R.id.home_page_recycler_view);
        this.toolbarHolder = (LinearLayout) root.findViewById(R.id.main_toolbar_holder);
        this.myToolbar = (Toolbar) root.findViewById(R.id.toolbarMainActivity);
        this.editText = (EditText) root.findViewById(R.id.editTextMainActivity);
        this.tablayoutHolder = (LinearLayout) getActivity().findViewById(R.id.main_tablayout_holder);
        this.swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.user_profile_home_tab_swipe_refresh_layout);
    }

    private void initRecyclerView()
    {
        this.layoutManager = new GridLayoutManager(getContext(), 2);
        this.itemsAdapter = new OfferItemsAdapterWithHeader(this.items);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 2, false));
        this.recyclerView.setAdapter(this.itemsAdapter);
        this.recyclerView.addOnScrollListener(new HidingScrollListener()
        {
            @Override
            public void onHide()
            {
                hideViews();
            }

            @Override
            public void onShow()
            {
                showViews();
            }
        });

        ItemClickSupportWithHeader.addTo(this.recyclerView).setOnItemClickListener(new ItemClickSupportWithHeader.OnItemClickListener()
        {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v)
            {
                if(recyclerView.getAdapter().getItemViewType(position) > 1)
                {
                    Intent intent = new Intent(getContext(), OffersActivity.class);
                    intent.putExtra(OFFERS_KEY, items.get(position));
                    System.out.println("trying to start offers activity");
                    startActivity(intent);
                }
            }
        });
    }

    private void initSwipeRefresh()
    {
        this.swipeRefreshLayout.setProgressViewOffset(false,
                getResources().getDimensionPixelOffset(R.dimen.home_tab_swipe_refresh_start),
                getResources().getDimensionPixelOffset(R.dimen.home_tab_swipe_refresh_end));
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                Toast.makeText(getActivity().getApplicationContext(), "Start refreshing list", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(true);
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity().getApplicationContext(), "End of refreshing", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }

    public void hideViews()
    {
        this.toolbarHolder.animate().translationY(-this.toolbarHolder.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.tablayoutHolder.getLayoutParams();
        int tabsBottomMargin = lp.bottomMargin;
        this.tablayoutHolder.animate().translationY(this.tablayoutHolder.getHeight()+tabsBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews()
    {
        this.toolbarHolder.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        this.tablayoutHolder.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private ArrayList<OffersItem> getItems()
    {
        Bitmap image;
        ArrayList<OffersItem> items = new ArrayList<>();

        for(int i = 0; i <= 10; i++)
        {
            image = BitmapFactory.decodeResource(getResources(), R.drawable.empty_img);
            items.add(new OffersItem(image, i + " km", i + " rubley", i + " description", i + " name", i + "date", i));
        }

        return items;
    }
}
