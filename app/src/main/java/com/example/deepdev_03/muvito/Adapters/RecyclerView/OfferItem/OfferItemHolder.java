package com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepdev_03.muvito.R;

public class OfferItemHolder extends RecyclerView.ViewHolder
{
    ImageView image;
    TextView distance, money, name;

    public OfferItemHolder(View itemView, ImageView image, TextView name, TextView distance, TextView money)
    {
        super(itemView);
        this.image = image;
        this.name = name;
        this.distance = distance;
        this.money = money;
    }

    public static OfferItemHolder newInstance(View parent) {
        ImageView image = (ImageView) parent.findViewById(R.id.thumbnail);
        TextView distance = (TextView) parent.findViewById(R.id.distance);
        TextView money = (TextView) parent.findViewById(R.id.price);
        TextView description = (TextView) parent.findViewById(R.id.name);
        return new OfferItemHolder(parent, image, description, distance, money);
    }
}
