package com.example.heiseyoumo.practiceapp.adapter;

/**
 * ToolbarActivity  对应的适配器
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.entity.FruitCard;
import com.example.heiseyoumo.practiceapp.entity.Image;
import com.example.heiseyoumo.practiceapp.ui.FruitInfoActivity;

import java.util.ArrayList;
import java.util.List;


public class FruitCardAdapter extends RecyclerView.Adapter<FruitCardAdapter.ViewHolder> {

    private Context mContext;
    private List<FruitCard> mList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView iv_img;
        private TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            iv_img = (ImageView)itemView.findViewById(R.id.iv_img);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
        }
    }

    //构造方法
    public FruitCardAdapter(Context mContext, List<FruitCard> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public FruitCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                          int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        //加载item布局
        View view = LayoutInflater.from(mContext).inflate
                (R.layout.fruit_card_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                FruitCard fruitCard = mList.get(position);
                Intent intent = new Intent(mContext, FruitInfoActivity.class);
                intent.putExtra(FruitInfoActivity.FRUIT_NAME,fruitCard.getName());
                intent.putExtra(FruitInfoActivity.FRUIT_IMAGE_ID,fruitCard.getImgId());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FruitCardAdapter.ViewHolder holder, int position) {
        FruitCard fruitCard = mList.get(position);
        holder.tv_name.setText(fruitCard.getName());
        //图片相素可能很高，不进行压缩就直接展示的话，很容易造成内存溢出，glide帮我们解决了这个烦恼
        Glide.with(mContext).load(fruitCard.getImgId()).into(holder.iv_img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
