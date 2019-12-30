package com.herokuapp.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "comments")
public class Comments implements Parcelable {

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };
    @PrimaryKey
    @SerializedName("id")
    @NonNull
    private String id;

    @ForeignKey(entity = Post.class, parentColumns = "id", childColumns = "postId")
    @SerializedName("postId")
    private String postId;
    @SerializedName("date")
    private String date;
    @SerializedName("body")
    private String body;
    @SerializedName("userName")
    private String userName;
    @SerializedName("email")
    private String email;
    @SerializedName("avatarUrl")
    private String avatarUrl;

    public Comments() {

    }

    protected Comments(@NonNull Parcel in) {
        id = in.readString();
        postId = in.readString();
        date = in.readString();
        body = in.readString();
        userName = in.readString();
        email = in.readString();
        avatarUrl = in.readString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(postId);
        dest.writeString(date);
        dest.writeString(body);
        dest.writeString(userName);
        dest.writeString(email);
        dest.writeString(avatarUrl);
    }
}
