package com.example.deepdev_03.muvito.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable
{
    private String name, location, date;
    private int rating;
    private Bitmap userAvatar;

    public UserData(String name, String location, String date, Bitmap userAvatar, int rating)
    {
        this.name = name;
        this.location = location;
        this.date = date;
        this.userAvatar = userAvatar;
        this.rating = rating;
    }

    protected UserData(Parcel in)
    {
        userAvatar = in.readParcelable(Bitmap.class.getClassLoader());
        name = in.readString();
        location = in.readString();
        date = in.readString();
        rating = in.readInt();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>()
    {
        @Override
        public UserData createFromParcel(Parcel in)
        {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size)
        {
            return new UserData[size];
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
        dest.writeParcelable(userAvatar, flags);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(date);
        dest.writeInt(rating);
    }

    public String getName()
    {
        return name;
    }

    public String getLocation()
    {
        return location;
    }

    public String getDate()
    {
        return date;
    }

    public int getRating()
    {
        return rating;
    }

    public Bitmap getUserAvatar()
    {
        return userAvatar;
    }
}
