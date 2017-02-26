package com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepdev_03.muvito.Model.OffersItem;
import com.example.deepdev_03.muvito.R;

import java.util.ArrayList;

public class OfferItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<OffersItem> items;

    public OfferItemsAdapter(ArrayList<OffersItem> items)
    {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_card, parent, false);
        return OfferItemHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        OfferItemHolder holder = (OfferItemHolder) viewHolder;
        OffersItem item = items.get(position);
        //holder.image.setImageBitmap(item.getImage());
        holder.distance.setText(item.getDistance());
        holder.money.setText(item.getMoney());
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
