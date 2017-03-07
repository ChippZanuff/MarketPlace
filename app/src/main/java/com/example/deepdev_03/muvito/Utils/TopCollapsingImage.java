package com.example.deepdev_03.muvito.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TopCollapsingImage extends ImageView
{
    public TopCollapsingImage(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public TopCollapsingImage(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public TopCollapsingImage(Context context)
    {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
