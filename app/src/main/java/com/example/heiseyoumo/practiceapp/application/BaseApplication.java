package com.example.heiseyoumo.practiceapp.application;

/**
 * 每当应用程序启动时，系统会自动将Application这个类进行初始化
 */

import android.app.Application;
import android.content.Context;

import com.example.heiseyoumo.practiceapp.utils.StaticUtils;

import org.litepal.LitePal;

import cn.bmob.v3.Bmob;


public class BaseApplication extends Application{
    //定义全局的context
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Bmob.initialize(mContext, StaticUtils.BMOBID);
        LitePal.initialize(mContext);
    }

    public static Context getContext(){
        return mContext;
    }
}
