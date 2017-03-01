package com.example.deepdev_03.muvito.Adapters.RecyclerView.UserData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepdev_03.muvito.Model.UserData;
import com.example.deepdev_03.muvito.R;

import java.util.ArrayList;

public class RatingUserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<UserData> items;

    public RatingUserListAdapter(ArrayList<UserData> items)
    {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.user_profile_reviewers_card_view, parent, false);
        return RatingUserListHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        RatingUserListHolder holder = (RatingUserListHolder) viewHolder;
        UserData item = items.get(position);
        //holder.image.setImageBitmap(item.getImage());
        holder.name.setText(item.getName());
        holder.date.setText(item.getDate());
        holder.ratingBar.setRating(3);
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
