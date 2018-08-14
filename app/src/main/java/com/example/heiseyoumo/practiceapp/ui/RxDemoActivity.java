package com.example.heiseyoumo.practiceapp.ui;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.heiseyoumo.practiceapp.R;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxDemoActivity extends AppCompatActivity {

    public static final String TAG = "RxDemoActivity";

    private TextView mTv;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo);

        initView();
    }

    private void initView() {
        mTv = (TextView)findViewById(R.id.mTv);
        mBtn = (Button)findViewById(R.id.mBtn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //event();
                finish();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"- - - > onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"- - - > onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"- - - > onDestroy()");
    }

    //    private void event() {
//        Observable.just("RxDemo")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        mTv.setText(s);
//                    }
//                });
//    }
}
