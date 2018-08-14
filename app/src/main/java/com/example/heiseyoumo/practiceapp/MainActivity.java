package com.example.heiseyoumo.practiceapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.ui.BaseActivity;
import com.example.heiseyoumo.practiceapp.ui.SecondActivity;
import com.example.heiseyoumo.practiceapp.utils.T;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_offline;

    private String data = "hello world";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                T.toast(MainActivity.this,"You clicked Add");
                break;
            case R.id.remove_item:
                T.toast(MainActivity.this,"You clicked Remove");
                break;
        }
        return true;
    }

    private void initView() {
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);

        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);

        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);

        btn_offline  =(Button)findViewById(R.id.btn_offline);
        btn_offline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                T.toast(MainActivity.this,"这是一个小测试！");
                break;
            case R.id.btn_2:
                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.btn_3:
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                //Intent intent2 = new Intent(Intent.ACTION_DIAL);
                //intent2.setData(Uri.parse("tel:10086"));
                intent2.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent2);
                break;
            case R.id.btn_offline:
                Intent intent = new Intent("com.example.broadcastpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("return");
                    Toast.makeText(this,returnData,Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


}
