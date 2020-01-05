package com.herokuapp.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


@Entity(tableName = "posts")
public class Post implements Parcelable {

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
    @PrimaryKey
    @SerializedName("id")
    @NonNull
    private String id;
    @SerializedName("title")
    private String title;
    @ForeignKey(entity = Author.class, parentColumns = "id", childColumns = "authorId")
    @SerializedName("authorId")
    private String authorId;
    @SerializedName("body")
    private String body;
    @SerializedName("date")
    private String date;
    @SerializedName("imageUrl")
    private String imageUrl;
    public Post() {

    }
    protected Post(@NonNull Parcel in) {
        id = in.readString();
        title = in.readString();
        authorId = in.readString();
        body = in.readString();
        date = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(authorId, post.authorId) &&
                Objects.equals(body, post.body) &&
                Objects.equals(date, post.date) &&
                Objects.equals(imageUrl, post.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorId, body, date, imageUrl);
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(authorId);
        dest.writeString(body);
        dest.writeString(date);
        dest.writeString(imageUrl);
    }
}
