package com.example.bohdan.provectustask.data;


import android.os.Parcel;
import android.os.Parcelable;

public class Results implements Parcelable {
    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
    private Picture picture;
    private Id id;
    private String phone;
    private String email;
    private Location location;
    private String registered;
    private String cell;
    private String dob;
    private Name name;
    private String gender;
    private String nat;
    private Login login;

    protected Results(Parcel in) {
        name = in.readParcelable(Name.class.getClassLoader());
        login = in.readParcelable(Login.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
        id = in.readParcelable(Id.class.getClassLoader());
        picture = in.readParcelable(Picture.class.getClassLoader());
        phone = in.readString();
        email = in.readString();
        registered = in.readString();
        cell = in.readString();
        dob = in.readString();
        gender = in.readString();
        nat = in.readString();
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "ClassPojo [picture = " + picture + ", id = " + id + ", phone = " + phone + ", email = " + email + ", location = " + location + ", registered = " + registered + ", cell = " + cell + ", dob = " + dob + ", name = " + name + ", gender = " + gender + ", nat = " + nat + ", login = " + login + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(name, flags);
        dest.writeParcelable(login, flags);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(id, flags);
        dest.writeParcelable(picture, flags);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(registered);
        dest.writeString(cell);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(nat);
    }
}
