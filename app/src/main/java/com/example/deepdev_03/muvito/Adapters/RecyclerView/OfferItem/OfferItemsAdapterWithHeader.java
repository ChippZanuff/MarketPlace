package com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepdev_03.muvito.Model.OffersItem;
import com.example.deepdev_03.muvito.R;

import java.util.ArrayList;

public class OfferItemsAdapterWithHeader extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final ArrayList<OffersItem> items;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    public OfferItemsAdapterWithHeader(ArrayList<OffersItem> items)
    {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_card, parent, false);
            return OfferItemHolder.newInstance(view);
        } else if (viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.viewholder_header, parent, false);
            return new HeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        if (!isPositionHeader(position))
        {
            OfferItemHolder holder = (OfferItemHolder) viewHolder;
            OffersItem item = items.get(position - 2);
            //holder.image.setImageBitmap(item.getImage());
            holder.distance.setText(item.getDistance());
            holder.money.setText(item.getMoney());
            holder.name.setText(item.getName());
        }
    }

    public int getBasicItemCount() {
        return items == null ? 0 : items.size();
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return getBasicItemCount() + 2;
    }

    //added a method that returns viewType for a given position
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    //added a method to check if given position is a header
    private boolean isPositionHeader(int position) {
        return position == 0 || position == 1;
    }

}
