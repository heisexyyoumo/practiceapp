package com.example.heiseyoumo.practiceapp.ui;

/**
 * WebView测试，进度条加载，本地加载，控制back键为返回上一个网页
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.heiseyoumo.practiceapp.R;

public class WebViewActivity extends AppCompatActivity {

    //private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
    }

    private void initView() {
        //mProgressBar = (ProgressBar)findViewById(R.id.mProgressBar);

        mWebView = (WebView)findViewById(R.id.mWebView);
        //支持JS
        mWebView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);

        /**
         * mWebView.setWebViewClient(new WebViewClient());
         mWebView.setWebChromeClient(new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        if(newProgress == 100){
        mProgressBar.setVisibility(View.GONE);
        }
        }
        });
         */
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
