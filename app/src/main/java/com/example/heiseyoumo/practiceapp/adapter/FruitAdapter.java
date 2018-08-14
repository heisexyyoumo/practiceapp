package com.example.heiseyoumo.practiceapp.adapter;

/**
 *
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;

import java.util.List;

import com.example.heiseyoumo.practiceapp.entity.Item;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

    private List<Item> mList;
    private LayoutInflater inflater;
    private Context mContext;


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.fruit_pic);
            mTextView = (TextView)itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(Context mContext,List<Item> mList){
        this.mList = mList;
        this.mContext = mContext;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fruit_item,null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Item item = mList.get(position);
                Toast.makeText(v.getContext(), "you click image " + item.getPicName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mList.get(position);
        holder.mImageView.setImageResource(item.getPicId());
        holder.mTextView.setText(item.getPicName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
