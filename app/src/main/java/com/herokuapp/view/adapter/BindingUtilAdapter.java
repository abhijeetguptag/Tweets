package com.herokuapp.view.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.herokuapp.R;
import com.squareup.picasso.Picasso;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class BindingUtilAdapter {

    private BindingUtilAdapter() {

    }

    @BindingAdapter({"avatarUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_broken_image_black_24dp).into(imageView);
    }

    @BindingAdapter("bindServerDate")
    public static void bindServerDate(@NonNull TextView textView, String date) {
        /*Parse string data and set it in another format for your textView*/

        textView.setText(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withZone(ZoneOffset.UTC)
                .format(OffsetDateTime.parse(date)));
    }
}