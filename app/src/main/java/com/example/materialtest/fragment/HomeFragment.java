package com.example.materialtest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.materialtest.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
  TabLayout tabLayout;
  ViewPager viewPager;
  List<Fragment> fgList = new ArrayList<>();
  List<String> list = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.home_fragment, container, false);

    tabLayout = view.findViewById(R.id.tab_layout);
    viewPager = view.findViewById(R.id.viewpager);

    fgList.add(new RecommendFragment());
    fgList.add(new FriutFragment());
    fgList.add(new PhoneFragment());
    fgList.add(new PCFragment());
    fgList.add(new PromotionFragment());
    fgList.add(new HomeDesignFragment());
    fgList.add(new ExerciseFragment());
    fgList.add(new BeautyMakeupFragment());


    list.add("推荐");
    list.add("水果");
    list.add("手机");
    list.add("电脑");
    list.add("特卖");
    list.add("家居");
    list.add("运动");
    list.add("美妆");

    MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fgList, list, getChildFragmentManager());
    viewPager.setAdapter(myFragmentAdapter);
//    将TabLayout与ViewPager绑定
    tabLayout.setupWithViewPager(viewPager);
    return view;
  }
}
