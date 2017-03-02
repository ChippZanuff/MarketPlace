package com.example.deepdev_03.muvito.Adapters.RecyclerView.OfferItem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepdev_03.muvito.R;

public class OfferItemHolder extends RecyclerView.ViewHolder
{
    ImageView image;
    TextView distance, money, name;
    ImageButton starButton;
    boolean starred = false;

    public OfferItemHolder(View itemView, ImageView image, TextView name, TextView distance, TextView money, ImageButton star)
    {
        super(itemView);
        this.image = image;
        this.name = name;
        this.distance = distance;
        this.money = money;
        this.starButton = star;
        this.starButton.setImageResource(R.drawable.ic_empty_star);
        this.starButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(starred)
                {
                    starButton.setImageResource(R.drawable.ic_empty_star);
                }
                else
                {
                    starButton.setImageResource(R.drawable.ic_full_star);
                }

                starred = !starred;
            }
        });
    }

    public static OfferItemHolder newInstance(View parent)
    {
        ImageView image = (ImageView) parent.findViewById(R.id.thumbnail);
        TextView distance = (TextView) parent.findViewById(R.id.distance);
        TextView money = (TextView) parent.findViewById(R.id.price);
        TextView description = (TextView) parent.findViewById(R.id.name);
        ImageButton star = (ImageButton) parent.findViewById(R.id.star);
        return new OfferItemHolder(parent, image, description, distance, money, star);
    }
}
