package com.example.heiseyoumo.practiceapp.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.heiseyoumo.practiceapp.R;


public class HeaderBar extends RelativeLayout implements View.OnClickListener {

    private Boolean isBackVisible;
    private String titleText;
    private String rightText;

    private ImageView mLeftIv;
    private TextView mCenterTv;
    private TextView mRightTv;

    public HeaderBar(Context context) {
        this(context, null);
    }

    public HeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        //将布局中的属性取出
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar);
        //取得xmi赋的值
        isBackVisible = ta.getBoolean(R.styleable.HeaderBar_isBackVisible, true);
        titleText = ta.getString(R.styleable.HeaderBar_titleText);
        rightText = ta.getString(R.styleable.HeaderBar_rightText);

        //将布局初始化
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_header_bar, this, true);
        mLeftIv = (ImageView) view.findViewById(R.id.mLeftIv);
        mCenterTv = (TextView) view.findViewById(R.id.mCenterTv);
        mRightTv = (TextView) view.findViewById(R.id.mRightTv);

        //根据xml布局中的值设置是否可见
        if (isBackVisible) {
            mLeftIv.setVisibility(View.VISIBLE);
        } else {
            mLeftIv.setVisibility(View.GONE);
        }
        mCenterTv.setText(titleText);
        mRightTv.setText(rightText);

        //设置点击事件
        mLeftIv.setOnClickListener(this);
        mRightTv.setOnClickListener(this);

        ta.recycle();

    }


    private ClickCallBack clickCallBack;


    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLeftIv:
                clickCallBack.onBackClick();
                return;
            case R.id.mRightTv:
                clickCallBack.onRightClick();
                return;
        }
    }


    //回调接口
    public interface ClickCallBack {
        void onBackClick();

        void onRightClick();

    }

    public TextView getRightTv() {
        return mRightTv;
    }

    public ImageView getLeftIv() {
        return mLeftIv;
    }


}
