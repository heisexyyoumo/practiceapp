package com.example.heiseyoumo.practiceapp.ui;

/**
 * 用RxVolley完成xml,json的解析,其中json解析借用GSON库
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.entity.APP;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class TestRxVolleyActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_volley);

        initView();
    }

    private void initView() {
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                //String url = "http://10.132.6.101:8080/heisexyyoumo/get_data.xml";
                String url = "http://10.132.6.101:8080/heisexyyoumo/examp.json";
                RxVolley.get(url, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        //parsingXml(t);
                        parsingJson(t);
                    }
                });
                break;
        }
    }

    //用Gson解析Json
    private void parsingJson(String t) {
        Gson gson = new Gson();
        /**
         *  解析数组要用gson.fromJson(t,new TypeToken<List<APP>>(){}.getType)
         *
         */
        APP app = gson.fromJson(t,APP.class);
        Log.e("TestRxVolleyActivity","VersionName is " + app.getVersionName());
        Log.e("TestRxVolleyActivity","VersionCode is " + app.getVersionCode());
        Log.e("TestRxVolleyActivity","Content is " + app.getContent());
        Log.e("TestRxVolleyActivity","url is " + app.getUrl());

    }

    //解析XML
    private void parsingXml(String t) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(t));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                        if ("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if ("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodeName)){
                            Log.e("TestRxVolleyActivity","id is " + id);
                            Log.e("TestRxVolleyActivity","name is " + name);
                            Log.e("TestRxVolleyActivity","version is " + version);
                        }
                        break;
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
