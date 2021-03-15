package com.example.materialtest.detailpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitActivity extends AppCompatActivity {
  public static final String FRUIT_NAME = "fruit_name";
  public static final String FRUIT_IMAGE = "fruit_image";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fruit);

//    通过Intent获取到传入的水果名和水果图片
    Intent intent = getIntent();
    String fruitName = intent.getStringExtra(FRUIT_NAME);
    int fruitImage = intent.getIntExtra(FRUIT_IMAGE, 0);

    Toolbar toolbar = findViewById(R.id.toolbar);
    CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
    ImageView fruitImageView = findViewById(R.id.fruit_image_view);
    TextView fruitContentText = findViewById(R.id.fruit_content_text);

    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    collapsingToolbar.setTitle(fruitName);
    Glide.with(this).load(fruitImage).into(fruitImageView);
    String fruitContent = generateFruitContent(fruitName);
    fruitContentText.setText(fruitContent);
  }

  /**
   * 循环fruitName 模拟内容详情
   *
   * @param fruitName
   * @return
   */
  private String generateFruitContent(String fruitName) {
    StringBuilder fruitContent = new StringBuilder();
    for (int i = 0; i < 500; i++) {
      fruitContent.append(fruitName);
    }
    return fruitContent.toString();
  }

  @Override
  /**
   * 处理HomeAsUp按钮的点击事件
   */
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish(); //关闭当前活动
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}