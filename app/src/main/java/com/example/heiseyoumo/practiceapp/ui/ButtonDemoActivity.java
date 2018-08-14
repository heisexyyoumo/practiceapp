package com.example.heiseyoumo.practiceapp.ui;

/*
    自定义的倒计时按钮,导航栏布局
 */

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.utils.ActivityCollector;
import com.example.heiseyoumo.practiceapp.utils.T;
import com.example.heiseyoumo.practiceapp.widgets.HeaderBar;
import com.example.heiseyoumo.practiceapp.widgets.VerifyCode;

public class ButtonDemoActivity extends BaseActivity{

    private VerifyCode mVerifyCode;
    private Handler handler;
    private int timeCount;
    private HeaderBar mHeaderBar;
    private EditText mMobileEt;
    private EditText mVerifyEt;
    private EditText mPwdEt;
    private Button mBtnTest;
    private Boolean isVisible;
    private Long pressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_demo);

        initView();
    }

    private void initView() {
        mVerifyCode = (VerifyCode) findViewById(R.id.mVerifyCode);
        mHeaderBar = (HeaderBar) super.findViewById(R.id.mHeaderBar);
        handler = new Handler();
        pressTime = Long.valueOf(0);
        mMobileEt = (EditText) findViewById(R.id.mMobileEt);
        mVerifyEt = (EditText) findViewById(R.id.mVerifyEt);
        mPwdEt = (EditText) findViewById(R.id.mPwdEt);
        mBtnTest = (Button)findViewById(R.id.mBtnTest);
        isVisible = false;

        mMobileEt.addTextChangedListener(this);
        mVerifyEt.addTextChangedListener(this);
        mPwdEt.addTextChangedListener(this);


        mHeaderBar.setClickCallBack(new HeaderBar.ClickCallBack() {
            @Override
            public void onBackClick() {
                Toast.makeText(ButtonDemoActivity.this, "click back"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(ButtonDemoActivity.this, "click right"
                        , Toast.LENGTH_SHORT).show();
            }
        });
        mVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerifyCode.start();
            }
        });
    }


//    private void start() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (timeCount > 0) {
//                    timeCount--;
//                    mVerifyCode.setText(timeCount + "s");
//                    handler.postDelayed(this,1000);
//                }
//            }
//        }, 1000);
//    }


    //在2s内连按两次back键退出程序
    @Override
    public void onBackPressed() {
        Long time = System.currentTimeMillis();
        if (time - pressTime > 2000) {
            Toast.makeText(this, "在按一次退出程序"
                    , Toast.LENGTH_SHORT).show();
            pressTime = time;
        } else {
            ActivityCollector.exitApp(this);
        }
    }


    //判断三个输入框是否为空
    public Boolean isNotEmpty() {
        return !mMobileEt.getText().toString().isEmpty() &&
                !mVerifyEt.getText().toString().isEmpty() &&
                !mPwdEt.getText().toString().isEmpty();
    }


    //重写edit改变的监听
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s, start, before, count);
        isVisible = isNotEmpty();
        updateState();
    }

    //更新按钮的状态
    private void updateState() {
        if (isVisible){
            mBtnTest.setVisibility(View.VISIBLE);
        }else {
            mBtnTest.setVisibility(View.GONE);
        }
    }
}
