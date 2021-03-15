package com.example.materialtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.materialtest.fragment.FindFragment;
import com.example.materialtest.fragment.HomeFragment;
import com.example.materialtest.fragment.MineFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.example.materialtest.fragment.ClassifyFragment;

import com.example.materialtest.fragment.MyFragmentAdapter;
import com.example.materialtest.fragment.ShoppingCartFragment;
import com.example.materialtest.location.Location;

/**
 * version:
 * Author:liuwei
 * Create Time:2020-12-31
 */

public class MainActivity extends AppCompatActivity {

  TabLayout tabLayout;
  ViewPager viewPager;
  List<Fragment> fgList = new ArrayList<>();
  List<String> list = new ArrayList<>();

  androidx.appcompat.widget.SearchView.SearchAutoComplete mSearchAutoComplete;
  SearchView searchView;

  private SwipeRefreshLayout swipeRefresh;
  private DrawerLayout mDrawerLayout;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = findViewById(R.id.toolbar);
    mDrawerLayout = findViewById(R.id.drawer_layout);
    NavigationView navView = findViewById(R.id.nav_view);
    FloatingActionButton fab = findViewById(R.id.fab);


    /**
     * Toolbar
     */
    setSupportActionBar(toolbar);
//    去掉标题
    getSupportActionBar().setDisplayShowTitleEnabled(false);


    /**
     * 导航按钮（滑动菜单）
     */
//    得到ActionBar的实例
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
//      让导航按钮显示出来
      actionBar.setDisplayHomeAsUpEnabled(true);
//      设置一个导航按钮图标 Toolbar最左侧的这个按钮就叫作HomeAsUp按钮
      actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
    }

    /**
     * 侧滑菜单导航视图
     */
//    将Call菜单项设置为默认选中
//    navView.setCheckedItem(R.id.nav_skin);
//    设置菜单项选中事件的监听器
    navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.nav_skin:
            Toast.makeText(MainActivity.this, "皮肤中心", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_friends:
            Toast.makeText(MainActivity.this, "好友", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_live:
            Toast.makeText(MainActivity.this, "直播", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_applet:
            Toast.makeText(MainActivity.this, "小程序", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_wallet:
            Toast.makeText(MainActivity.this, "钱包", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_location:
            Intent intent = new Intent(MainActivity.this, Location.class);
            startActivity(intent);
        }
        mDrawerLayout.closeDrawers();
        return true;
      }
    });

    /**
     * 悬浮按钮点击事件
     */
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Snackbar.make(v, "Data delete", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
          }
        }).show();
      }
    });



    /*swipeRefresh = findViewById(R.id.swipe_refresh);
//    设置下拉进度条的颜色
    swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
//    调用setOnRefreshListener()方法来设置一个下拉刷新的监听器
    swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        refreshFruits();
      }
    });
*/

    tabLayout = findViewById(R.id.tab_layout);
    viewPager = findViewById(R.id.viewpager);

    fgList.add(new HomeFragment());
    fgList.add(new ClassifyFragment());
    fgList.add(new FindFragment());
    fgList.add(new ShoppingCartFragment());
    fgList.add(new MineFragment());

    list.add("首页");
    list.add("分类");
    list.add("发现");
    list.add("购物车");
    list.add("我的");

    MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fgList, list, getSupportFragmentManager());
    viewPager.setAdapter(myFragmentAdapter);
//    将TabLayout与ViewPager绑定
    tabLayout.setupWithViewPager(viewPager);

////    初始化选项卡图标
//    tabLayout.getTabAt(0).setIcon(R.drawable.home_pressed);
//    tabLayout.getTabAt(1).setIcon(R.drawable.classify_normal);
//    tabLayout.getTabAt(2).setIcon(R.drawable.find_normal);
//    tabLayout.getTabAt(3).setIcon(R.drawable.shoppingcart_normal);
//    tabLayout.getTabAt(4).setIcon(R.drawable.mine_normal);

//    修改默认选项页为第三张  同时要修改选项卡图标
//    viewPager.setCurrentItem(0);
//
//    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//      @Override
////      选中时
//      public void onTabSelected(TabLayout.Tab tab) {
//        if (tab.getPosition() == 0) {
//          tab.setIcon(R.drawable.home_pressed);
//        } else if (tab.getPosition() == 1) {
//          tab.setIcon(R.drawable.classify_pressed);
//        } else if (tab.getPosition() == 2) {
//          tab.setIcon(R.drawable.find_pressed);
//        } else if (tab.getPosition() == 3) {
//          tab.setIcon(R.drawable.shoppingcart_pressed);
//        } else tab.setIcon(R.drawable.mine_pressed);
//
//      }
//
//      @Override
////      退出时
//      public void onTabUnselected(TabLayout.Tab tab) {
//        if (tab.getPosition() == 0) {
//          tab.setIcon(R.drawable.home_normal);
//        } else if (tab.getPosition() == 1) {
//          tab.setIcon(R.drawable.classify_normal);
//        } else if (tab.getPosition() == 2) {
//          tab.setIcon(R.drawable.find_normal);
//        } else if (tab.getPosition() == 3) {
//          tab.setIcon(R.drawable.shoppingcart_normal);
//        } else tab.setIcon(R.drawable.mine_normal);
//      }
//
//      @Override
//      public void onTabReselected(TabLayout.Tab tab) {
//
//      }
//    });
    tabLayout.getTabAt(0).setIcon(R.drawable.selector_icon_home);
    tabLayout.getTabAt(1).setIcon(R.drawable.selector_icon_classify);
    tabLayout.getTabAt(2).setIcon(R.drawable.selector_icon_find);
    tabLayout.getTabAt(3).setIcon(R.drawable.selector_icon_shoppingcart);
    tabLayout.getTabAt(4).setIcon(R.drawable.selector_icon_mine);


  }


  @Override
  /**
   *  加载toolbar.xml这个菜单文件
   */
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//    searchView.setIconified(false);//设置searchView处于展开状态
//    searchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
//    searchView.setIconifiedByDefault(false);//默认为true在框内，设置false则在框外

    return true;
  }

  @Override
//  处理各个按钮的点击事件
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
//      HomeAsUp按钮的id永远都是android.R.id.home
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);
        break;
      case R.id.scan:
        Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
        break;
      case R.id.share:
        Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        break;
      case R.id.settings:
        Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
        break;
      default:
    }
    return true;
  }


  /**
   * 下拉刷新
   */
 /* private void refreshFruits() {
    new Thread(new Runnable() {
      @Override
      public void run() {
//        将线程沉睡两秒钟,因为本地刷新操作速度非常快，如果不将线程沉睡,刷新立刻就结束了，从而看不到刷新的过程
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
//        将线程切换回主线程
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            initFruits();
            adapter.notifyDataSetChanged();
            refreshLayout.setEnableRefresh(false);
          }
        });
      }
    }).start();
  }*/

}