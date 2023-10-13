package com.example.spacexlaunchtrackerapp.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.spacexlaunchtrackerapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public  class Utility {

    public static String getDateFormated(String date)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
            return formatter.format(sdf.parse(date));
        }catch (Exception e){
            return "";
        }
    }

    public static void loadUrlImage(Context context, String mImageUrl, ImageView mImageView) {
        if (mImageView != null) {
            try {
                if (mImageUrl==null) {
                    mImageView.setImageResource(R.drawable.rocket_launch_image);
                } else {
                    Glide.with(context).load(mImageUrl).apply (
                            RequestOptions.placeholderOf(R.drawable.rocket_launch_image)).apply(RequestOptions.errorOf(R.drawable.rocket_launch_image)).into(mImageView);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
