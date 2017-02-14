package com.example.deepdev_03.muvito.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class OffersItem implements Parcelable
{
    private Bitmap image;
    private String name;
    private String distance;
    private String money;
    private String description;
    private String date;
    private int reviews;
    private int likes;

    public OffersItem(Bitmap image, String distance, String money, String description, String name, String date, int reviews)
    {
        this.image = image;
        this.distance = distance;
        this.money = money;
        this.description = description;
        this.name = name;
        this.date = date;
        this.reviews = reviews;
    }

    protected OffersItem(Parcel in)
    {
        image = in.readParcelable(Bitmap.class.getClassLoader());
        distance = in.readString();
        money = in.readString();
        description = in.readString();
        date = in.readString();
        reviews = in.readInt();
        likes = in.readInt();
    }

    public static final Creator<OffersItem> CREATOR = new Creator<OffersItem>()
    {
        @Override
        public OffersItem createFromParcel(Parcel in)
        {
            return new OffersItem(in);
        }

        @Override
        public OffersItem[] newArray(int size)
        {
            return new OffersItem[size];
        }
    };


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(image, flags);
        dest.writeString(distance);
        dest.writeString(money);
        dest.writeString(description);
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

    public String getName()
    {
        return name;
    }
}
