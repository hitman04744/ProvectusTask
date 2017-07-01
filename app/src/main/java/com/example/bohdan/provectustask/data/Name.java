
package com.example.bohdan.provectustask.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Name implements Parcelable {
    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
    private String title;
    private String last;
    private String first;

    protected Name(Parcel in) {
        title = in.readString();
        last = in.readString();
        first = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "ClassPojo [title = " + title + ", last = " + last + ", first = " + first + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(last);
        dest.writeString(first);
    }
}