package com.example.deepdev_03.muvito.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepdev_03.muvito.Model.UserData;
import com.example.deepdev_03.muvito.R;

import java.util.ArrayList;

public class BlackListAdapter extends BaseAdapter
{
    private ArrayList<UserData> list;
    private LayoutInflater inflater;
    private Context context;

    public BlackListAdapter(Context context, ArrayList<UserData> list)
    {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return this.list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view == null)
        {
            view = this.inflater.inflate(R.layout.user_profile_black_list_item_holder, parent, false);
        }

        UserData user = list.get(position);

        ((TextView) view.findViewById(R.id.black_list_user_name)).setText(user.getName());
        ((TextView) view.findViewById(R.id.black_list_ban_date)).setText(user.getDate());
        ((ImageView) view.findViewById(R.id.black_list_user_avatar)).setImageBitmap(this.getCroppedBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.devka)));

        return view;
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
