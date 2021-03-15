package com.example.materialtest.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.materialtest.R;

import java.util.ArrayList;
import java.util.List;

public class Location extends AppCompatActivity {
  public LocationClient mLocationClient;
  private TextView positionText;
  private MapView mapView;
  private BaiduMap baiduMap;
  private boolean isFirstLocate = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    调用getApplicationContext()方法来获取一个全局的Context
    mLocationClient = new LocationClient(getApplicationContext());
//    调用LocationClient的registerLocationListener()方法来注册一个定位监听器，当获取到位置信息的时候，就会回调这个定位监听器
    mLocationClient.registerLocationListener(new MyLocationListener());

    SDKInitializer.initialize(getApplicationContext());

    setContentView(R.layout.activity_location);

    positionText = (TextView) findViewById(R.id.position_text_view);

    mapView = (MapView) findViewById(R.id.bmapView);
    baiduMap = mapView.getMap();
    baiduMap.setMyLocationEnabled(true);

//    创建一个空的List集合，然后依次判断这3个权限有没有被授权，如果没被授权就添加到List集合中，最后将List转换成数组，再调用ActivityCompat.requestPermissions()方法一次性申请
    List<String> permissionList = new ArrayList<>();
    if (ContextCompat.checkSelfPermission(Location.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
    }
    if (ContextCompat.checkSelfPermission(Location.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
      permissionList.add(Manifest.permission.READ_PHONE_STATE);
    }
    if (ContextCompat.checkSelfPermission(Location.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
    if (!permissionList.isEmpty()) {
      String[] permissions = permissionList.toArray(new String[permissionList.size()]);
      ActivityCompat.requestPermissions(Location.this, permissions, 1);
    } else {
      requestLocation();
    }
  }

  private void requestLocation() {
    initLocation();
//    开启定位
    mLocationClient.start();
  }

  private void initLocation() {
    LocationClientOption option = new LocationClientOption();
//    每几秒更新当前位置
    option.setScanSpan(3000);
//    强制指定只使用GPS进行定位
    option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
//    获取当前位置详细的地址信息
    option.setIsNeedAddress(true);
    mLocationClient.setLocOption(option);
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mLocationClient.stop();
    mapView.onDestroy();
    baiduMap.setMyLocationEnabled(false);
  }

  @Override
  /**
   * 申请权限
   */
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case 1:
        if (grantResults.length > 0) {
          for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
              Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
              finish();
              return;
            }
          }
          requestLocation();
        } else {
          Toast.makeText(this, "发生位置错误", Toast.LENGTH_SHORT).show();
          finish();
        }
        break;
      default:
    }
  }

  /**
   * 让地图移动到设备所在位置
   *
   * @param location
   */
  private void navigateTo(BDLocation location) {
    if (isFirstLocate) {
      LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
      MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
      baiduMap.animateMapStatus(update);
//      设置缩放级别
      update = MapStatusUpdateFactory.zoomTo(16f);
      baiduMap.animateMapStatus(update);
      isFirstLocate = false;
    }
    MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
    locationBuilder.latitude(location.getLatitude());
    locationBuilder.longitude(location.getLongitude());
//    在地图上显示“我”
    MyLocationData locationData = locationBuilder.build();
    baiduMap.setMyLocationData(locationData);
  }

  public class MyLocationListener implements BDLocationListener {
    @Override
//    获取位置信息
    public void onReceiveLocation(BDLocation bdLocation) {

      if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
        navigateTo(bdLocation);
      }

      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          StringBuilder currentPosition = new StringBuilder();
          currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
          currentPosition.append("经线：").append(bdLocation.getLongitude()).append("\n");
          currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
          currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
          currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
          currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
          currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
          currentPosition.append("定位方式：");
          if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
            currentPosition.append("GPS");
          } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
            currentPosition.append("网络");
          }
          positionText.setText(currentPosition);
        }
      });
    }

    public void onConnectHotSpotMessage(String s, int i) {

    }
  }
}