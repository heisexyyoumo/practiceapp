package com.example.heiseyoumo.practiceapp.ui;

/**
 * 新闻头条
 */

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.adapter.NewsHeadAdapter;
import com.example.heiseyoumo.practiceapp.entity.NewsHeadLine;
import com.example.heiseyoumo.practiceapp.utils.StaticUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private List<NewsHeadLine> mList = new ArrayList<>();
    //网页的地址
    private List<String> mListUrl = new ArrayList<>();
    private RecyclerView recycle_view;
    private NewsHeadAdapter adapter;
    //下拉刷新
    private SwipeRefreshLayout sr_refresh;
    //新闻头条的接口地址
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initView();
    }

    private void initView() {
        recycle_view = (RecyclerView)findViewById(R.id.recycle_view);
        //指定RecycleView的布局方式，此处为线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycle_view.setLayoutManager(layoutManager);
        //分割线
        recycle_view.addItemDecoration(new DividerItemDecoration
                (this,DividerItemDecoration.VERTICAL));

        sr_refresh = (SwipeRefreshLayout)findViewById(R.id.sr_refresh);
        //刷新进度条的颜色
        sr_refresh.setColorSchemeResources(R.color.colorPrimary);
        //下拉刷新的监听事件
        sr_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNewsHeadline();
            }
        });

        initNewsHeadline();



    }


    //刷新
    private void refreshNewsHeadline() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initNewsHeadline();
                        adapter.notifyDataSetChanged();
                        sr_refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    //初始化新闻头条
    private void initNewsHeadline() {

        //拼接url
        url = "http://v.juhe.cn/toutiao/index?type=top&key="
                + StaticUtils.NEWS_HEAD_KEY;

        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parsingJson(t);
            }
        });
    }

    //解析json
    private void parsingJson(String t) {
        mList.clear();
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject json = (JSONObject)jsonArray.get(i);
                NewsHeadLine data = new NewsHeadLine();
                data.setTitle(json.getString("title"));
                data.setSource(json.getString("author_name"));
                data.setDate(json.getString("date"));
                data.setImgUrl(json.getString("thumbnail_pic_s"));
                mList.add(data);
                mListUrl.add(json.getString("url"));
            }
            adapter = new NewsHeadAdapter(this,mList);
            recycle_view.setAdapter(adapter);
            adapter.setOnItemClickListener(new NewsHeadAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    //注意item的position是从0开始的
                    //Log.e("NewsActivity","position = " + position);

                    Intent intent = new Intent(view.getContext(),
                            WebViewActivity.class);
                    intent.putExtra("url",mListUrl.get(position));
                    startActivity(intent);


                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
