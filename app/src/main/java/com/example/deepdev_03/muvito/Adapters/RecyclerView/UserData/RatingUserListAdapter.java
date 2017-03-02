package com.example.deepdev_03.muvito.Adapters.RecyclerView.UserData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
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
    private Context context;

    public RatingUserListAdapter(ArrayList<UserData> items)
    {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        this.context = parent.getContext();
        final View view = LayoutInflater.from(this.context).inflate(R.layout.user_profile_reviewers_card_view, parent, false);
        return RatingUserListHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        RatingUserListHolder holder = (RatingUserListHolder) viewHolder;
        UserData item = items.get(position);
        holder.avatar.setImageBitmap(this.getCroppedBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.devka)));
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

    private Bitmap getCroppedBitmap(Bitmap bitmap)
    {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
