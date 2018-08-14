package com.example.heiseyoumo.practiceapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.adapter.ImageAdapter;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.example.heiseyoumo.practiceapp.entity.Image;

public class ImageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Image> mList = new ArrayList<>();
    private ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        String welfare = null;
        int number = 50;
        int page = 1;
        try {

            welfare = URLEncoder.encode(getString(R.string.text_welfare), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://gank.io/api/data/"+welfare+"/"+number+"/" +page;


        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parsingJson(t);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++){
                Image data = new Image();
                JSONObject json = (JSONObject)jsonArray.get(i);
                String url = json.getString("url");
                String who = json.getString("who");
                data.setImageUrl(url);
                data.setImageWho(who);
                Log.i("url",url);
                Log.i("who",who);
                mList.add(data);
            }

            adapter = new ImageAdapter(this,mList);
            mRecyclerView.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
