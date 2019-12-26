package com.herokuapp.data.entity;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;



import com.google.gson.annotations.SerializedName;

@Entity(tableName = "address")
public class Address implements Parcelable {

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;

    public Address() {

    }

    public Address(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Address(Parcel in) {
        latitude = in.readString();
        longitude = in.readString();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}
