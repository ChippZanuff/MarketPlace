package com.example.deepdev_03.muvito.Adapters.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepdev_03.muvito.R;

public class Viewholder extends RecyclerView.ViewHolder
{
    public ImageView image;
    public TextView distance, money, description;

    public Viewholder(View itemView, ImageView image, TextView description, TextView distance, TextView money)
    {
        super(itemView);
        this.image = image;
        this.description = description;
        this.distance = distance;
        this.money = money;
    }

    public static Viewholder newInstance(View parent) {
        ImageView image = (ImageView) parent.findViewById(R.id.offers_item_image);
        TextView distance = (TextView) parent.findViewById(R.id.offers_item_distance);
        TextView money = (TextView) parent.findViewById(R.id.offers_item_money_sum);
        TextView description = (TextView) parent.findViewById(R.id.offers_item_description);
        return new Viewholder(parent, image, description, distance, money);
    }
}
