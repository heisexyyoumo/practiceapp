package com.example.heiseyoumo.practiceapp.ui;

/**
 * 测试notification（通知）的一系列功能
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.heiseyoumo.practiceapp.R;

import java.io.File;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_notification;
    private NotificationManager manager;
    private Intent intent;
    private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initView();

    }

    private void initView() {
        btn_notification = (Button)findViewById(R.id.btn_notification);
        btn_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_notification:
                intent = new Intent(this,NotificationTextActivity.class);
                pi = PendingIntent.getActivity(this,0,intent,0);
                manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                /**
                 * 注意Notification notification = new NotificationCompat.Builder(this)已被弃用
                 */
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(this,"default")
                        .setContentTitle("这是标题")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("这是一个超级" +
                                "超级超级超级超级超级超级超级超级超级超级超级超级" +
                                "超级超级超级超级超级超级长文本"))
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture
                                //(BitmapFactory.decodeResource(getResources(),R.drawable.welcome)))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.
                                decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)//延时启动intent
                        //.setAutoCancel(true)   //取消通知方式1
                        .setSound(Uri.fromFile(new File("/system/media/audio" +
                                "/ringtones/ringtone_001.ogg")))//设置提示音
                        .setVibrate(new long[]{0,1000,1000,1000})//设置振动
                        .setLights(Color.BLUE,1000,1000)//设置闪光
                        .setPriority(NotificationCompat.PRIORITY_MAX);//设置通知等级;
                manager.notify(1,builder.build());
                break;

        }
    }
}
