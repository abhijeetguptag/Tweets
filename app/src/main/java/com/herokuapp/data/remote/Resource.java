package com.herokuapp.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class Resource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    private final String message;


    public int totalDataAvailable;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message, int totalDataAvailable) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.totalDataAvailable=totalDataAvailable;
    }

    public static <T> Resource<T> success(@NonNull T data ) {
        return new Resource<>(Status.SUCCESS, data, null,0);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg,0);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null,0);
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public static <T> Resource<T> success(@NonNull T data , int totalCount) {
        return new Resource<>(Status.SUCCESS, data, null,totalCount);
    }

}
