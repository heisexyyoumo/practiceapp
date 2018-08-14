package com.example.heiseyoumo.practiceapp.ui;

/**
 * 对activity的生命周期进行分析（正常|异常）
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.heiseyoumo.practiceapp.R;

public class AnalysisCircleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AnalysisCircleActivity";
    private Button btn_transfer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_circle);

        initView();

        //测试异常情况下activity的生命周期------资源相关的配置发生改变
        if (savedInstanceState != null){
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG,"[onCreate] restore extra_test:" + test);
        }
    }

    //初始化布局
    private void initView() {

        btn_transfer = (Button)findViewById(R.id.btn_transfer);
        btn_transfer.setOnClickListener(this);

        //将View(Button)右移200px(200像素)
        //前提：父布局（LinearLayout）方向为水平方向，View有具体的width，height值。
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                btn_transfer.getLayoutParams();
        params.width += 200;
        params.leftMargin += 200;
        btn_transfer.setLayoutParams(params);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        outState.putString("extra_test","test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG,"[onRestoreInstanceState] restore extra_test:" + test);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG,"onConfigurationChanged,newOrientation" + newConfig.orientation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_transfer:
                Intent intent = new Intent(AnalysisCircleActivity.this,
                        AnalysisCircleActivity.class);
                intent.putExtra("time",System.currentTimeMillis());
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent, time = " + intent.getLongExtra
                ("time",0));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
