package com.example.heiseyoumo.practiceapp.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/*
    我的自定义view
    设置一个长宽均为100dp的view
 */
public class MyView extends View {


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = setView(100, widthMeasureSpec);
        int height = setView(100, heightMeasureSpec);

        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    private int setView(int defaultSize, int measureSpec) {
        int mSize = defaultSize;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {
                mSize = defaultSize;
                break;
            }

            case MeasureSpec.EXACTLY: {
                mSize = size;
            }

            case MeasureSpec.AT_MOST: {
                mSize = size;
            }

        }
        return mSize;
    }

}

