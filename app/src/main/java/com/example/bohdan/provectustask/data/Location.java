
package com.example.bohdan.provectustask.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
    private String street;
    private String state;
    private String postcode;
    private String city;

    protected Location(Parcel in) {
        street = in.readString();
        state = in.readString();
        postcode = in.readString();
        city = in.readString();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ClassPojo [street = " + street + ", state = " + state + ", postcode = " + postcode + ", city = " + city + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(state);
        dest.writeString(postcode);
        dest.writeString(city);
    }
}

