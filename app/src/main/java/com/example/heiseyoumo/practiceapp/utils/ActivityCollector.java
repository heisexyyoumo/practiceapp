package com.example.heiseyoumo.practiceapp.utils;

/**
 * activity的管理类
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private static List<Activity> mList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        mList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mList.remove(activity);
    }

    public static void finishAllActivity() {
        for (Activity activity : mList) {
            if (activity.isFinishing()) {
                activity.finish();
            }
        }
        mList.clear();
    }

    public static void exitApp(Context context) {
        finishAllActivity();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //杀死进程
        assert am != null;
        am.killBackgroundProcesses(context.getPackageName());
        //0表示正常退出
        System.exit(0);

    }

}
