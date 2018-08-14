package com.example.heiseyoumo.practiceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.entity.NewsHeadLine;
import com.example.heiseyoumo.practiceapp.ui.WebViewActivity;

import java.util.List;

public class NewsHeadAdapter extends RecyclerView.Adapter<NewsHeadAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<NewsHeadLine> mList;
    private LayoutInflater inflater;

    //声明接口变量
    private OnItemClickListener mOnItemClickListener;

    public NewsHeadAdapter(Context mContext, List<NewsHeadLine> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_source;
        private TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView)itemView.findViewById(R.id.iv_img);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            tv_source = (TextView)itemView.findViewById(R.id.tv_source);
            tv_date = (TextView)itemView.findViewById(R.id.tv_date);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = inflater.inflate(R.layout.newshead_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //将position保存在itemView的tag中，一边点击时获取
        holder.itemView.setTag(position);

        NewsHeadLine headLine = mList.get(position);
        holder.tv_title.setText(headLine.getTitle());
        holder.tv_source.setText(headLine.getSource());
        holder.tv_date.setText(headLine.getDate());
        if (!TextUtils.isEmpty(headLine.getImgUrl())){
            Glide.with(mContext).load(headLine.getImgUrl()).into(holder.iv_img);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    //声明一个类似ListView的接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //暴露给外面的调用者，定义一个设置Listener的方法()
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
