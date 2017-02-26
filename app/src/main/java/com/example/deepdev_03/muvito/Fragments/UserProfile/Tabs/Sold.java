package com.example.deepdev_03.muvito.Fragments.UserProfile.Tabs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem.GridSpacingItemDecoration;
import com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem.OfferItemsAdapter;
import com.example.deepdev_03.muvito.Model.OffersItem;
import com.example.deepdev_03.muvito.OffersActivity;
import com.example.deepdev_03.muvito.R;
import com.example.deepdev_03.muvito.Utils.ItemClickSupportWithHeader;

import java.util.ArrayList;

public class Sold extends Fragment
{
    private final String OFFERS_KEY = "offerItem";
    private RecyclerView recyclerView;
    private ArrayList<OffersItem> items;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout tablayoutHolder;
    private OfferItemsAdapter itemsAdapter;
    private NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.user_profile_sold_tab, container, false);
        this.findViews(rootView);
        this.items = getItems();
        this.initRecyclerView();
        this.initNestedScroll();

        return rootView;
    }

    private void findViews(View root)
    {
        this.recyclerView = (RecyclerView) root.findViewById(R.id.user_profile_sold_recycler_view);
        this.tablayoutHolder = (LinearLayout) getActivity().findViewById(R.id.main_tablayout_holder);
        this.nestedScrollView = (NestedScrollView) root.findViewById(R.id.user_profile_sold_nested_scroll);
    }

    private void initRecyclerView()
    {
        this.layoutManager = new GridLayoutManager(getContext(), 2);
        this.itemsAdapter = new OfferItemsAdapter(this.items);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 2, false));
        this.recyclerView.setAdapter(this.itemsAdapter);
        this.recyclerView.setNestedScrollingEnabled(false);

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

    public void hideViews()
    {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.tablayoutHolder.getLayoutParams();
        int tabsBottomMargin = lp.bottomMargin;
        this.tablayoutHolder.animate().translationY(this.tablayoutHolder.getHeight()+tabsBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews()
    {
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

    private void initNestedScroll()
    {
        this.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener()
        {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
            {
                if(oldScrollY < scrollY)
                {
                    hideViews();
                }
                if(oldScrollY > scrollY)
                {
                    showViews();
                }
            }
        });
    }
}
