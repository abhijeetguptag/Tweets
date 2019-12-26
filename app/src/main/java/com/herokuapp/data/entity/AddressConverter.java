package com.herokuapp.data.entity;


import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class AddressConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static Address toAddress(String addressAsJson) {
        return gson.fromJson(addressAsJson, Address.class);
    }

    @TypeConverter
    public static String fromAddress(Address address) {
        return gson.toJson(address);
    }
}
