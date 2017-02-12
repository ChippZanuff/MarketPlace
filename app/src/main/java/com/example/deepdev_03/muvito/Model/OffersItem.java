package com.example.deepdev_03.muvito.Model;

import android.graphics.Bitmap;

public class OffersItem
{
    private Bitmap image;
    private String distance;
    private String money;
    private String description;

    public OffersItem(Bitmap image, String distance, String money, String description)
    {
        this.image = image;
        this.distance = distance;
        this.money = money;
        this.description = description;
    }

    public Bitmap getImage()
    {
        return image;
    }

    public String getDistance()
    {
        return distance;
    }

    public String getMoney()
    {
        return money;
    }

    public String getDescription()
    {
        return description;
    }
}
