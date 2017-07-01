
package com.example.bohdan.provectustask.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {
    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
    private String thumbnail;
    private String medium;
    private String large;

    protected Picture(Parcel in) {
        thumbnail = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public String toString() {
        return "ClassPojo [thumbnail = " + thumbnail + ", medium = " + medium + ", large = " + large + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumbnail);
        dest.writeString(medium);
        dest.writeString(large);
    }
}
