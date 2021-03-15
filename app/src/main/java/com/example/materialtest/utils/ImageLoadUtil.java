package com.example.materialtest.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

/**
 * 图片加载封装类
 */
public class ImageLoadUtil {
  public static void imageLoad(Context context,String url, ImageView imageView){
//    一、Glide框架
    Glide.with(context).load(url).into(imageView);
//    二Picasso框架
    Picasso.with(context).load(url).into(imageView);
  }
}
