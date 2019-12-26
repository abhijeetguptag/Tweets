package com.herokuapp.view.adapter;


import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class BindingUtilAdapter
{
    @BindingAdapter({"bind:avatarUrl"})
    public static void loadImage(ImageView imageView, String url)
    {
        Picasso.get().load(url).into(imageView);
    }

    @BindingAdapter("bindServerDate")
    public static void bindServerDate(@NonNull TextView textView, String date) {
        /*Parse string data and set it in another format for your textView*/

        textView.setText(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withZone(ZoneOffset.UTC)
                        .format(OffsetDateTime.parse((CharSequence)date)));
    }
}