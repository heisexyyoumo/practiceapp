package com.example.heiseyoumo.practiceapp.widgets;

/*
    自定义带倒计时的Button
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class VerifyCode extends Button{

    private Handler mHandler;
    private int timeCount;
    private Boolean isVisible;

    public VerifyCode(Context context) {
        super(context);
        initView();
    }

    public VerifyCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VerifyCode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHandler = new Handler();
        timeCount = 10;
        isVisible = true;
        VerifyCode.this.setText("获取验证码");
        VerifyCode.this.setClickable(isVisible);

    }

    public void start(){
        isVisible = false;
        VerifyCode.this.setClickable(isVisible);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timeCount > 0){
                    VerifyCode.this.setText(timeCount + "s");
                    timeCount--;
                    //一秒后执行runnable
                    mHandler.postDelayed(this,1000);
                }else {
                    resetButton();
                }
            }
        },0);
    }

    private void resetButton() {
        isVisible = true;
        VerifyCode.this.setText("重获验证码");
        VerifyCode.this.setClickable(isVisible);
        timeCount = 10;
    }

}
