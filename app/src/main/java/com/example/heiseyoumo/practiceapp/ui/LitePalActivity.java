package com.example.heiseyoumo.practiceapp.ui;

/**
 * 测试LitePal数据库的操作
 */

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.heiseyoumo.practiceapp.R;

import org.litepal.LitePal;

public class LitePalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        initView();

    }

    private void initView() {
        btn_create = (Button)findViewById(R.id.btn_create);
        btn_create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                LitePal.getDatabase();
                break;
        }
    }
}
