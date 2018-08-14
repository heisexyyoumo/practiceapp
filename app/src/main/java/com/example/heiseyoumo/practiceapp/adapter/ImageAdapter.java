package com.example.heiseyoumo.practiceapp.adapter;

import android.content.Context;
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

import java.util.List;

import com.example.heiseyoumo.practiceapp.entity.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Image> mLst;
    private Context mContext;
    private LayoutInflater inflater;
    public ImageAdapter(Context mContext,List<Image> mLst){
        this.mContext = mContext;
        this.mLst = mLst;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_girl;
        private TextView tv_desc;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            iv_girl = (ImageView)itemView.findViewById(R.id.iv_girl);
        }
    }
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Image data = mLst.get(position);
        holder.tv_desc.setText(data.getImageWho());
        if(!TextUtils.isEmpty(data.getImageUrl())){
            Glide.with(mContext).load(data.getImageUrl()).into(holder.iv_girl);
        }


    }

    @Override
    public int getItemCount() {
        return mLst.size();
    }


}
