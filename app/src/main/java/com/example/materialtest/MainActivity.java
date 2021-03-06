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
//    ????????????
    getSupportActionBar().setDisplayShowTitleEnabled(false);


    /**
     * ??????????????????????????????
     */
//    ??????ActionBar?????????
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
//      ???????????????????????????
      actionBar.setDisplayHomeAsUpEnabled(true);
//      ?????????????????????????????? Toolbar?????????????????????????????????HomeAsUp??????
      actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
    }

    /**
     * ????????????????????????
     */
//    ???Call??????????????????????????????
//    navView.setCheckedItem(R.id.nav_skin);
//    ???????????????????????????????????????
    navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.nav_skin:
            Toast.makeText(MainActivity.this, "????????????", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_friends:
            Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_live:
            Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_applet:
            Toast.makeText(MainActivity.this, "?????????", Toast.LENGTH_SHORT).show();
            break;
          case R.id.nav_wallet:
            Toast.makeText(MainActivity.this, "??????", Toast.LENGTH_SHORT).show();
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
     * ????????????????????????
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
//    ??????????????????????????????
    swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
//    ??????setOnRefreshListener()?????????????????????????????????????????????
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

    list.add("??????");
    list.add("??????");
    list.add("??????");
    list.add("?????????");
    list.add("??????");

    MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fgList, list, getSupportFragmentManager());
    viewPager.setAdapter(myFragmentAdapter);
//    ???TabLayout???ViewPager??????
    tabLayout.setupWithViewPager(viewPager);

////    ????????????????????????
//    tabLayout.getTabAt(0).setIcon(R.drawable.home_pressed);
//    tabLayout.getTabAt(1).setIcon(R.drawable.classify_normal);
//    tabLayout.getTabAt(2).setIcon(R.drawable.find_normal);
//    tabLayout.getTabAt(3).setIcon(R.drawable.shoppingcart_normal);
//    tabLayout.getTabAt(4).setIcon(R.drawable.mine_normal);

//    ?????????????????????????????????  ??????????????????????????????
//    viewPager.setCurrentItem(0);
//
//    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//      @Override
////      ?????????
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
////      ?????????
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
   *  ??????toolbar.xml??????????????????
   */
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//    searchView.setIconified(false);//??????searchView??????????????????
//    searchView.onActionViewExpanded();// ?????????????????????????????????????????????????????????
//    searchView.setIconifiedByDefault(false);//?????????true??????????????????false????????????

    return true;
  }

  @Override
//  ?????????????????????????????????
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
//      HomeAsUp?????????id????????????android.R.id.home
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);
        break;
      case R.id.scan:
        Toast.makeText(this, "?????????", Toast.LENGTH_SHORT).show();
        break;
      case R.id.share:
        Toast.makeText(this, "??????", Toast.LENGTH_SHORT).show();
        break;
      case R.id.settings:
        Toast.makeText(this, "??????", Toast.LENGTH_SHORT).show();
        break;
      default:
    }
    return true;
  }


  /**
   * ????????????
   */
 /* private void refreshFruits() {
    new Thread(new Runnable() {
      @Override
      public void run() {
//        ????????????????????????,??????????????????????????????????????????????????????????????????,?????????????????????????????????????????????????????????
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
//        ???????????????????????????
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