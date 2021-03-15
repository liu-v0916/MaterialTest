package com.example.materialtest.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
  public CustomViewPager(@NonNull Context context) {
    super(context);
  }

  public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return false;
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return false;
  }

  @Override
  public void setCurrentItem(int item) {
    super.setCurrentItem(item, false);
  }
}
