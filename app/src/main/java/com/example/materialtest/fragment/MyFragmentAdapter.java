package com.example.materialtest.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentAdapter extends FragmentPagerAdapter {
  List<Fragment> fgList;
  List<String> list;

  public MyFragmentAdapter(List<Fragment> fgList, List<String> list, FragmentManager fm) {
    super(fm);
    this.fgList = fgList;
    this.list = list;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return fgList.get(position);
  }

  @Override
  public int getCount() {
    return fgList.size();
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return list.get(position);
  }
}
