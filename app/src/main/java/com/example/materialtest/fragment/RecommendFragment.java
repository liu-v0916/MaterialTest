package com.example.materialtest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.materialtest.bean.Fruit;
import com.example.materialtest.FruitAdapter;
import com.example.materialtest.R;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendFragment extends Fragment {

  public static RecommendFragment newInstance(String label) {
    Bundle args = new Bundle();
    args.putString("label", label);
    RecommendFragment fragment = new RecommendFragment();
    fragment.setArguments(args);
    return fragment;
  }

//  TabLayout tabLayout;
  private Context context;
  private SmartRefreshLayout refreshLayout;
  private Banner banner;
  private Fruit[] fruits = {new Fruit("苹果", R.mipmap.apple), new Fruit("香蕉", R.mipmap.banana),
          new Fruit("橘子", R.mipmap.orange), new Fruit("西瓜", R.mipmap.watermelon),
          new Fruit("梨", R.mipmap.pear), new Fruit("葡萄", R.mipmap.grape),
          new Fruit("菠萝", R.mipmap.pineapple), new Fruit("草莓", R.mipmap.strawberry),
          new Fruit("樱桃", R.mipmap.cherry), new Fruit("芒果", R.mipmap.mango)};
  private List<Fruit> fruitList = new ArrayList<>();

//  private String[] tabs = {"推荐", "水果", "手机", "电脑", "特卖", "家居", "运动", "美妆"};
  private List<RecommendFragment> tabFragmentList = new ArrayList<>();


  private FruitAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.recommend_fragment, container, false);
//    tabLayout = view.findViewById(R.id.tab_layout);
    RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
    refreshLayout = view.findViewById(R.id.srl);
    initFruits();
    GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new FruitAdapter(fruitList);
    recyclerView.setAdapter(adapter);

    refreshLayout.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh(RefreshLayout refreshLayout) {
        adapter.refreshData(initFruits());
        refreshLayout.finishRefresh(1000);  //完成刷新，结束刷新动画
      }
    });

    refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
      @Override
      public void onLoadMore(RefreshLayout refreshLayout) {
        adapter.loadMore(moreData());
        refreshLayout.finishLoadMore(1000);
      }
    });

    //添加tab
//    for (int i = 0; i < tabs.length; i++) {
//      tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
//      tabFragmentList.add(RecommendFragment.newInstance(tabs[i]));
//    }

    initWithBanner(view);
    return view;
  }

  /**
   * 初始化数据
   */
  private List<Fruit> initFruits() {
    fruitList.clear();
    for (int i = 0; i < 20; i++) {
      Random random = new Random();
      int index = random.nextInt(fruits.length);
      fruitList.add(fruits[index]);
    }
    return fruitList;
  }

  /**
   * 加载更多
   *
   * @return
   */
  private List<Fruit> moreData() {
    for (int i = 0; i < 10; i++) {
      Random random = new Random();
      int index = random.nextInt(fruits.length);
      fruitList.add(fruits[index]);
    }
    return fruitList;
  }

  //轮播
  private void initWithBanner(View view) {
    ArrayList<Integer> images = new ArrayList<>();
    images.add(R.mipmap.banner01);
    images.add(R.mipmap.banner02);
    images.add(R.mipmap.banner03);

    banner = view.findViewById(R.id.banner);
    banner.setBannerAnimation(Transformer.ZoomOut);
    banner.setDelayTime(6000);
    banner.setImages(images).setImageLoader(new ImageLoader() {
      @Override
      public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
      }
    }).start();
  }

}
