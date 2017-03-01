package com.example.deepdev_03.muvito.Adapters.RecyclerView.UserData;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.deepdev_03.muvito.R;

public class RatingUserListHolder extends RecyclerView.ViewHolder
{
    ImageView avatar;
    TextView name, date;
    RatingBar ratingBar;

    public RatingUserListHolder(View itemView, ImageView image, TextView name, TextView date, RatingBar ratingBar)
    {
        super(itemView);
        this.avatar = image;
        this.name = name;
        this.date = date;
        this.ratingBar = ratingBar;
    }

    public static RatingUserListHolder newInstance(View parent) {
        ImageView image = (ImageView) parent.findViewById(R.id.user_profile_reviewers_avatar);
        TextView name = (TextView) parent.findViewById(R.id.user_profile_reviewers_name);
        RatingBar ratingBar = (RatingBar) parent.findViewById(R.id.user_profile_guest_rating_bar);
        TextView date = (TextView) parent.findViewById(R.id.user_profile_review_date);
        return new RatingUserListHolder(parent, image, name, date, ratingBar);
    }
}
