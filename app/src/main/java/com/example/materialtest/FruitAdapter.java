package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.materialtest.detailpage.FruitActivity;

import java.util.List;

import com.example.materialtest.bean.Fruit;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
  private Context mContent;
  private List<Fruit> mFruitList;

  static class ViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView fruitImage;
    TextView fruitName;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      cardView = (CardView) itemView;
      fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
      fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
    }
  }

  /**
   * 把要展示的数据源传进来，并赋值给mFruitList
   *
   * @param fruitList
   */
  public FruitAdapter(List<Fruit> fruitList) {
    mFruitList = fruitList;
  }

  @NonNull
  @Override
  /**
   * 创建ViewHolder实列
   */
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (mContent == null) {
      mContent = parent.getContext();
    }
    View view = LayoutInflater.from(mContent).inflate(R.layout.fruit_item, parent, false);
    final ViewHolder holder = new ViewHolder(view);

    /**
     * 点击事件
     */
    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int position = holder.getAdapterPosition();
        Fruit fruit = mFruitList.get(position);
        Intent intent = new Intent(mContent, FruitActivity.class);
        intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
        intent.putExtra(FruitActivity.FRUIT_IMAGE, fruit.getImage());
        mContent.startActivity(intent);
      }
    });

//    return new ViewHolder(view);
    return holder;
  }

  @Override
  /**
   * 对RecyclerView子项的数据进行赋值
   */

  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Fruit fruit = mFruitList.get(position);
    holder.fruitName.setText(fruit.getName());
//    加载图片
//    Glide.with 传入一个Context、Activity或Fragment
//    load() 可以是一个URL地址，也可以是一个本地路径，或者是一个资源id
//    into() 设置到具体某一个ImageView中
    Glide.with(mContent).load(fruit.getImage()).into(holder.fruitImage);
//    Picasso.with(mContent).load(fruit.getImage()).into(holder.fruitImage);

//    ImageLoadUtil.imageLoad(mContent, fruit.getImage(), holder.fruitImage);
  }

  @Override
  /**
   * 返回数据源的长度
   */
  public int getItemCount() {
    return mFruitList.size();
  }

  public void refreshData(List<Fruit> fruits) {
//    mFruitList.addAll(0,fruits);
    notifyDataSetChanged();
  }

  public void loadMore(List<Fruit> fruits) {
//    mFruitList.addAll(fruits);
    notifyDataSetChanged();
  }

}
